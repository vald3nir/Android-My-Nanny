package com.vald3nir.mynanny.views.monitor;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.controllers.MQTTController;

import org.eclipse.paho.client.mqttv3.MqttMessage;

class MonitorDelegate {

    private final MQTTController controller;

    public MonitorDelegate(MonitorFragment fragment) {
        controller = MQTTController.getInstance();
        controller.registerListener(new MQTTController.IMQTTController() {
            @Override
            public void successConnectionMQTT() {

            }

            @Override
            public void onMQTTException(Exception exception) {
                fragment.showAlertNotification(exception.getMessage());
            }

            @Override
            public void responseMQTT(String topic, MqttMessage messageMQTT) {

                if (fragment.getString(R.string.topic_baby_temperature).equalsIgnoreCase(topic)) {
                    fragment.setValueBabyTemperature(new String(messageMQTT.getPayload()) + " °C");

                } else if (fragment.getString(R.string.topic_humidity).equalsIgnoreCase(topic)) {
                    fragment.setValueHumidity(new String(messageMQTT.getPayload()) + " %");

                } else if (fragment.getString(R.string.topic_temperature).equalsIgnoreCase(topic)) {
                    fragment.setValueTemperature(new String(messageMQTT.getPayload()) + " °C");

                } else if (fragment.getString(R.string.topic_alarm_baby_fever).equalsIgnoreCase(topic)) {
                    fragment.showAlertDialog(R.drawable.baby_fever, R.string.your_baby_has_a_fever);

                } else if (fragment.getString(R.string.topic_alarm_baby_woke_up).equalsIgnoreCase(topic)) {
                    fragment.showAlertDialog(R.drawable.baby_crying, R.string.your_baby_woke_up);
                } else if (fragment.getString(R.string.topic_light).equalsIgnoreCase(topic)) {
                    Integer value = Integer.valueOf(new String(messageMQTT.getPayload()));
                    fragment.setValueLight(value > 50);
                }
            }
        });
    }

    public void subscribeTopics(MonitorFragment fragment) {
        int[] qos = {0, 0, 0, 0, 0, 0};
        String[] topics = {
                fragment.getString(R.string.topic_alarm_baby_fever),
                fragment.getString(R.string.topic_alarm_baby_woke_up),
                fragment.getString(R.string.topic_baby_temperature),
                fragment.getString(R.string.topic_humidity),
                fragment.getString(R.string.topic_temperature),
                fragment.getString(R.string.topic_light)
        };
        controller.subscribe(topics, qos);
    }

    public void publishValueLight(MonitorFragment fragment, String value) {
        controller.publish(fragment.getString(R.string.topic_publish_light), value);
    }
}
