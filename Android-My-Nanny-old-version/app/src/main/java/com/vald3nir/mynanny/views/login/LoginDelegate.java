package com.vald3nir.mynanny.views.login;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.controllers.MQTTController;
import com.vald3nir.mynanny.utils.AppUtils;

import org.eclipse.paho.client.mqttv3.MqttMessage;

class LoginDelegate {

    private final MQTTController controller;

    LoginDelegate(LoginActivity activity) {
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

        this.controller.subscribe(activity.getString(R.string.topic_login_response));
    }

    private void validateResponse(String topic, String payload, LoginActivity activity) {
        if (activity.getString(R.string.topic_login_response).equalsIgnoreCase(topic) &&
                activity.getString(R.string.topic_login_ok).equalsIgnoreCase(payload)) {
            activity.callMainActivity();
        } else {
            activity.showAlertNotification(activity.getString(R.string.username_or_password_invalid));
        }
    }

    void login(LoginActivity activity, String email, String password) {
        if (AppUtils.validateFields(activity, email, password, password)) {
            controller.publish(activity.getString(R.string.topic_login_request), AppUtils.createParam(email, password));
        }
    }
}
