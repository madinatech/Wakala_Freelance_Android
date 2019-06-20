package com.os.paytzwakal.reg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.os.paytzwakal.reg.R;
import com.os.paytzwakal.reg.application.App;
import com.os.paytzwakal.reg.application.Config;
import com.os.paytzwakal.reg.application.Util;
import com.os.paytzwakal.reg.pojo.UserResponse;
import com.os.paytzwakal.reg.service.ApiService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout constraintLayout;
    EditText et_mobile, et_passcode;
    private UserResponse userResponse;
    private String mobile_number, pass_code;
    private String android_id;
    private String MD5_Hash_String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Fabric.with(this, new Crashlytics());
        if (Config.getIsfirstlogin().equalsIgnoreCase("true")) {
            Intent i = new Intent(LoginActivity.this, RegistarionFormListActivity.class);
            startActivity(i);
            finish();
        }
        init();
    }

    private void init() {
        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        constraintLayout = findViewById(R.id.constraintLayout);
        et_mobile = findViewById(R.id.et_mobile);
        et_passcode = findViewById(R.id.et_passcode);
        et_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = et_mobile.getText().toString();
                int textLength = et_mobile.getText().length();
                if (text.endsWith("-") || text.endsWith(" ") || text.endsWith(" "))
                    return;
                if (textLength == 1) {
                    if (!text.contains("0")) {
                        et_mobile.setText(new StringBuilder(text).insert(text.length() - 1, "0").toString());
                        et_mobile.setSelection(et_mobile.getText().length());
                    }
                } else if (textLength == 5) {
                    if (!text.contains(" ")) {
                        et_mobile.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                        et_mobile.setSelection(et_mobile.getText().length());
                    }
                } else if (textLength == 9) {

                    et_mobile.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                    et_mobile.setSelection(et_mobile.getText().length());

                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                try {
                    if (validateform()) {
                        if (Util.hasInternet(this)) {
                            Util.showProDialog(LoginActivity.this);
                            mobile_number = et_mobile.getText().toString().trim();
                            pass_code = et_passcode.getText().toString().trim();
                            String mobile = mobile_number.replaceAll("[() ]", "");
                            mobile = mobile.startsWith("0") ? mobile.replaceFirst("0", "") : mobile;

                            HashMap<String, Object> myHashMap = new HashMap<String, Object>();
                            myHashMap.put("mobile_number", mobile);
                            myHashMap.put("passcode", pass_code);
                            myHashMap.put("device_id", android_id);
                            myHashMap.put("device_type", "android");

                            ApiService apiService = App.getInitialClient().create(ApiService.class);
                            Call<UserResponse> call = apiService.login_call(myHashMap);
                            call.enqueue(new Callback<UserResponse>() {
                                @Override
                                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                    if (response.body() != null) {
                                        userResponse = response.body();
                                        if (userResponse.getResponse().getStatus()) {
                                            Config.setUserId(userResponse.getResponse().getData().get(0).getFreelancerId());
                                            Config.setIsfirstlogin("true");
                                           /* MD5_Hash_String = md5(userResponse.getResponse().getData().get(0).getFreelancerId());

                                            Config.setMD5Encode(MD5_Hash_String);
                                            */
                                            Config.setRememberToken(userResponse.getResponse().getData().get(0).getRememberToken());
                                            Util.dismissProDialog();
                                            startActivity(new Intent(LoginActivity.this, RegistarionFormListActivity.class));
                                            finish();
                                        } else {
//                                        isCalling = false;
                                            Util.dismissProDialog();
                                            et_passcode.setText("");
                                            et_passcode.requestFocus();
                                            Util.showErrorSnackBar(constraintLayout, userResponse.getResponse().getMessage(), LoginActivity.this);
                                            if (userResponse.getResponse().getUnderMaintenance().equalsIgnoreCase("1")) {
//                                                Util.doLogout(constraintLayout, LoginActivity.this, LoginActivity.this);

                                            } else if (userResponse.getResponse().getIsDeactivate().equalsIgnoreCase("1")) {
//                                                Util.doLogout(constraintLayout, LoginActivity.this, LoginActivity.this);
                                            } else if (userResponse.getResponse().getSflag().equalsIgnoreCase("1")) {
                                                Util.doSFlagLogout(constraintLayout, LoginActivity.this, LoginActivity.this);
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserResponse> call, Throwable t) {
                                    Log.e("fail", t.toString());
                                    Util.dismissProDialog();
                                    Util.showErrorSnackBar(constraintLayout, userResponse.getResponse().getMessage(), LoginActivity.this);
                                }
                            });
                        } else {
                            Util.showErrorSnackBar(constraintLayout, getResources().getString(R.string.no_internet), LoginActivity.this);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

        }
    }

    private boolean validateform() {
        if (et_mobile.getText().toString().isEmpty()) {
            Util.showErrorSnackBar(constraintLayout, "Please enter mobile number", this);
            return false;
        } else if (et_mobile.getText().toString().length() < 10) {
            Util.showErrorSnackBar(constraintLayout, "Please enter valid mobile number", this);
            return false;
        } else if (et_passcode.getText().toString().isEmpty()) {
            Util.showErrorSnackBar(constraintLayout, "Please enter passcode", this);
            return false;
        }
        return true;
    }

    private String md5(String in) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
