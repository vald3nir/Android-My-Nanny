package com.vald3nir.mynanny.views.register;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.controllers.MQTTController;
import com.vald3nir.mynanny.utils.AppUtils;

import org.eclipse.paho.client.mqttv3.MqttMessage;

class RegisterDelegate {

    private final MQTTController controller;

    RegisterDelegate(RegisterActivity activity) {
        this.controller = MQTTController.getInstance();
        this.controller.registerListener(new MQTTController.IMQTTController() {
            @Override
            public void successConnectionMQTT() {

            }

            @Override
            public void onMQTTException(Exception exception) {
                activity.showAlertNotification(exception.getMessage());
            }

            @Override
            public void responseMQTT(String topic, MqttMessage messageMQTT) {
                validateResponse(topic, new String(messageMQTT.getPayload()), activity);
            }
        });

        this.controller.subscribe(activity.getString(R.string.topic_register_response));
    }

    void register(RegisterActivity activity, String email, String password, String confirmPassword) {
        if (AppUtils.validateFields(activity, email, password, confirmPassword)) {
            controller.publish(activity.getString(R.string.topic_register_request), AppUtils.createParam(email, password));
        }
    }

    private void validateResponse(String topic, String payload, RegisterActivity activity) {
        if (activity.getString(R.string.topic_register_response).equalsIgnoreCase(topic) &&
                activity.getString(R.string.topic_register_ok).equalsIgnoreCase(payload)) {

            activity.onBackPressed();

        } else {
            activity.showAlertNotification(activity.getString(R.string.user_already_exists));
        }
    }


}
