package com.vald3nir.mynanny.controllers;

import android.content.Context;

import com.vald3nir.mynanny.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MQTTController {


    private MqttAndroidClient mqttClient;
    private IMQTTController callback;
    private static MQTTController controller;

    public static MQTTController getInstance() {
        if (controller == null) controller = new MQTTController();
        return controller;
    }

    public void registerListener(IMQTTController callback) {
        this.callback = callback;
    }


    public void connect(Context context) {

        this.mqttClient = new MqttAndroidClient(context, context.getString(R.string.address_broker_mqtt), context.getString(R.string.client_id_mqtt)
        );

        this.mqttClient.setCallback(new MqttCallbackExtended() {

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                if (callback != null) callback.successConnectionMQTT();
            }

            @Override
            public void connectionLost(Throwable throwable) {
                if (callback != null) {
                    callback.onMQTTException(new Exception(throwable.getMessage()));
                }
            }

            @Override
            public void messageArrived(String topic, MqttMessage messageMQTT) {
                if (callback != null) {


                    System.out.println(messageMQTT);


                    callback.responseMQTT(topic, messageMQTT);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);

        try {

            mqttClient.connect(mqttConnectOptions, null, new IMqttActionListener() {

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);

                    mqttClient.setBufferOpts(disconnectedBufferOptions);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable throwable) {
                    if (callback != null) {
                        callback.onMQTTException(new Exception(throwable.getMessage()));
                    }
                }
            });


        } catch (Exception e) {
            if (callback != null) {
                callback.onMQTTException(e);
            }
        }
    }

    public void subscribe(String[] topic, int[] qos) {

        try {

            mqttClient.subscribe(topic, qos);

        } catch (Exception e) {
            notifyException(e);
        }
    }

    public void subscribe(String topic) {

        try {

            mqttClient.subscribe(topic, 0, null, new IMqttActionListener() {

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    System.out.println(asyncActionToken);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable throwable) {
                    if (callback != null) {
                        callback.onMQTTException(new Exception(throwable.getMessage()));
                    }
                }
            });

        } catch (Exception e) {
            notifyException(e);
        }
    }

    private void notifyException(Exception e) {
        if (callback != null) callback.onMQTTException(e);
    }

    public void publish(String topic, String payload) {

        try {

            MqttMessage message = new MqttMessage();
            message.setPayload(payload.getBytes());
            mqttClient.publish(topic, message);

        } catch (Exception e) {
            notifyException(e);
        }
    }

    public interface IMQTTController {

        void successConnectionMQTT();

        void onMQTTException(Exception exception);

        void responseMQTT(String topic, MqttMessage messageMQTT);
    }


}
