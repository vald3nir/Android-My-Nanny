package com.vald3nir.mynanny.views.splash;

import com.vald3nir.mynanny.controllers.MQTTController;

import org.eclipse.paho.client.mqttv3.MqttMessage;

class SplashDelegate {

    private final MQTTController mqttController;

    SplashDelegate() {
        this.mqttController = MQTTController.getInstance();
    }

    void connectMQTT(SplashActivity splashActivity) {
        this.mqttController.registerListener(new MQTTController.IMQTTController() {
            @Override
            public void successConnectionMQTT() {
                splashActivity.callLogin();
            }

            @Override
            public void onMQTTException(Exception exception) {

            }

            @Override
            public void responseMQTT(String topic, MqttMessage messageMQTT) {

            }
        });

        this.mqttController.connect(splashActivity);
    }
}
