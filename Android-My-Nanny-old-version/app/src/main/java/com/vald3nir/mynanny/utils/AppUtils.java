package com.vald3nir.mynanny.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.views.custom.CustomActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class AppUtils {

    public static String createParam(String email, String password) {
        JSONObject json = new JSONObject();
        try {
            json.put("name", email).put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    private static boolean checkFieldValueInvalid(String email) {
        return (email == null || email.trim().isEmpty());
    }

    public static boolean validateFields(CustomActivity activity, String email, String password, String confirmPassword) {

        if (checkFieldValueInvalid(email)) {
            activity.showAlertNotification(R.string.email_not_valid);
            return false;

        } else if (checkFieldValueInvalid(password)) {
            activity.showAlertNotification(R.string.password_not_valid);
            return false;

        } else if (!password.equalsIgnoreCase(confirmPassword)) {
            activity.showAlertNotification(R.string.password_not_equals);
            return false;
        }

        return true;
    }

    public static String getValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static void saveURLStreaming(Context context, String url) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url_streaming", url);
        editor.apply();
    }

    public static String getURLStreaming(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        return sharedPreferences.getString("url_streaming", context.getString(R.string.url_streaming));
    }

}
