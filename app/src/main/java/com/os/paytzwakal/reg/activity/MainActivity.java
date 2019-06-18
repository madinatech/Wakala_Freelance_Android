package com.os.paytzwakal.reg.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.os.paytzwakal.reg.R;
import com.os.paytzwakal.reg.application.App;
import com.os.paytzwakal.reg.application.Compressor;
import com.os.paytzwakal.reg.application.Config;
import com.os.paytzwakal.reg.application.Util;
import com.os.paytzwakal.reg.database.RegistartionData;
import com.os.paytzwakal.reg.database.RegistrationDatabase;
import com.os.paytzwakal.reg.pojo.BankResponse;
import com.os.paytzwakal.reg.pojo.CityResponse;
import com.os.paytzwakal.reg.pojo.RegistrationResponse;
import com.os.paytzwakal.reg.service.ApiService;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_name, et_email, et_mobile, et_benificary_name, et_pin, et_contact_person, et_account_number, et_contactnumber, et_location;
    Spinner et_bank, et_city;
    ConstraintLayout constraintLayout;
    List<BankResponse.Response.Datum> banklist;
    String name, email, mobile, bank, bank_account_number, benificary_name, city, pin, contact_person, contact_number, location;
    TextView tv_id_doc, tv_tin_doc, tv_license_doc, tv_permit_1_doc, tv_agreement_doc;
    int button_click;
    String email_patern = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    LinearLayout ll_hint_spinner;
    EditText et_cpin;
    int citypos;
    private String mobile_number;
    private RegistrationResponse registrationResponse;
    private String city_id;
    private String bank_id;
    private String device_id;
    private BankResponse bankResponse;
    private CityResponse cityResponse;
    private List<CityResponse.Response.Datum> citylist;
    private String cityname, bankname;
    private Dialog dialog;
    private Button mCamerabtn, mGallerybtn, btnCancell;
    private File imageFile, imageFile1, imageFile2, imageFile3, imageFile4;
    private Uri outputFileUri;
    private Bitmap imageBitmap = null;
    private String selectedImagePath;
    private TextView tv;

    public static File compressImage(Context context, File actualImage) {
        File compressedImage = null;
        try {
            compressedImage = new Compressor(context)
                    .setMaxWidth(300)
                    .setMaxHeight(300)
                    .setQuality(75)
                    .setCompressFormat(Bitmap.CompressFormat.WEBP)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .compressToFile(actualImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compressedImage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        getCityList();
        getBankList();
    }

    private void init() {

        ll_hint_spinner = findViewById(R.id.ll_hint_spinner);
        et_name = findViewById(R.id.et_name);
        et_name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        et_email = findViewById(R.id.et_email);
        et_cpin = findViewById(R.id.et_cpin);
        et_mobile = findViewById(R.id.et_mobile);
        et_bank = findViewById(R.id.et_bank);
        et_benificary_name = findViewById(R.id.et_benificary_name);
        et_benificary_name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        et_city = findViewById(R.id.et_city);
        et_pin = findViewById(R.id.et_pin);
        et_contact_person = findViewById(R.id.et_contact_person);
        et_contact_person.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        et_account_number = findViewById(R.id.et_account_number);
        et_contactnumber = findViewById(R.id.et_contactnumber);
        et_location = findViewById(R.id.et_location);
        constraintLayout = findViewById(R.id.constraintLayout);
        tv_id_doc = findViewById(R.id.id_doc);
        tv_tin_doc = findViewById(R.id.tin_doc);
        tv_license_doc = findViewById(R.id.license_doc);
        tv_permit_1_doc = findViewById(R.id.permit_1_doc);
        tv_agreement_doc = findViewById(R.id.agreement_doc);
        device_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
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

        et_contactnumber.addTextChangedListener(new TextWatcher() {
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
                String text = et_contactnumber.getText().toString();
                int textLength = et_contactnumber.getText().length();
                if (text.endsWith("-") || text.endsWith(" ") || text.endsWith(" "))
                    return;
                if (textLength == 1) {
                    if (!text.contains("0")) {
                        et_contactnumber.setText(new StringBuilder(text).insert(text.length() - 1, "0").toString());
                        et_contactnumber.setSelection(et_contactnumber.getText().length());
                    }
                } else if (textLength == 5) {
                    if (!text.contains(" ")) {
                        et_contactnumber.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                        et_contactnumber.setSelection(et_contactnumber.getText().length());
                    }
                } else if (textLength == 9) {

                    et_contactnumber.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                    et_contactnumber.setSelection(et_contactnumber.getText().length());

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imvBack: {
                finish();
                break;
            }
            case R.id.btn_submit: {
                alertDailog();
                break;
            }
            case R.id.id_doc: {
                button_click = 1;

                try {
                    if (android.os.Build.VERSION.SDK_INT >= 23) {
                        askPermission();
                    } else {
                        showPopmenu();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            }
            case R.id.tin_doc: {
                button_click = 2;
                try {
                    if (android.os.Build.VERSION.SDK_INT >= 23) {
                        askPermission();
                    } else {
                        showPopmenu();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            }
            case R.id.agreement_doc: {
                button_click = 5;
                try {
                    if (android.os.Build.VERSION.SDK_INT >= 23) {
                        askPermission();
                    } else {
                        showPopmenu();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            }
            case R.id.license_doc: {
                button_click = 3;
                try {
                    if (android.os.Build.VERSION.SDK_INT >= 23) {
                        askPermission();
                    } else {
                        showPopmenu();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            }
            case R.id.permit_1_doc: {
                button_click = 4;
                try {
                    if (android.os.Build.VERSION.SDK_INT >= 23) {
                        askPermission();
                    } else {
                        showPopmenu();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            }
            case R.id.cameradialogbtn: {
                Config.setCameraOn(true);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFile = new File(Environment.getExternalStorageDirectory(), "Paytz_Wakala" + System.currentTimeMillis() + ".jpg");
                outputFileUri = Uri.fromFile(imageFile);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    outputFileUri = FileProvider.getUriForFile(MainActivity.this, MainActivity.this.getApplicationContext().getPackageName() + ".com.os.paytzwakala.provider", imageFile);
                } else {
                    outputFileUri = Uri.fromFile(imageFile);
                }
                Log.d("TAG", "outputFileUri intent" + outputFileUri);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(intent, 0);
                dialog.cancel();
                break;
            }


            case R.id.gallerydialogbtn: {
                Config.setCameraOn(true);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
                dialog.cancel();
                break;
            }
            case R.id.canceldialogbtn: {
                Config.setCameraOn(false);
                dialog.cancel();
                break;
            }
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Log.d("TAG", "outputFileUri RESULT_OK" + outputFileUri);
                    if (outputFileUri != null) {
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            InputStream ims = null;
                            try {
                                ims = MainActivity.this.getContentResolver().openInputStream(outputFileUri);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            imageBitmap = BitmapFactory.decodeStream(ims);
                        } else {
//                            imageBitmap = decodeSampledBitmapFromUri(outputFileUri, addkyc.getWidth(), addkyc.getHeight(), Env.currentActivity);
                        }
                        if (imageBitmap == null) {
                        } else {
                            if (imageBitmap != null)
//                                cropPopmenu(imageBitmap);
                                if (imageFile != null) {
                                    selectedImagePath = imageFile.getAbsolutePath();// outputFileUri.getPath().
                                    imageBitmap = Util.rotateImageBitmap(selectedImagePath, imageBitmap);
                                    //imageFile = Util.compressImage(Env.currentActivity, imageFile);
                                }

                        }
                    }
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    outputFileUri = data.getData();
                    try {
                        selectedImagePath = Util.getRealPathFromURI(MainActivity.this, outputFileUri);
                        imageFile = new File(selectedImagePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("TAG", "datae" + outputFileUri);
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        InputStream ims = null;
                        try {
                            ims = MainActivity.this.getContentResolver().openInputStream(outputFileUri);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        imageBitmap = BitmapFactory.decodeStream(ims);
                    } else {
//                        imageBitmap = decodeSampledBitmapFromUri(outputFileUri, addkyc.getWidth(), addkyc.getHeight(), Env.currentActivity);
                    }
                    if (imageBitmap == null) {
                    } else {
                        if (imageBitmap != null)
//                            cropPopmenu(imageBitmap);
                            if (imageFile != null) {
                                selectedImagePath = imageFile.getAbsolutePath();// outputFileUri.getPath().
                                imageBitmap = Util.rotateImageBitmap(selectedImagePath, imageBitmap);
                                //imageFile = Util.compressImage(Env.currentActivity, imageFile);
                            }

                    }
                }
                break;
            default:
                break;
        }

        if (resultCode == RESULT_OK) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                InputStream ims = null;
                try {
                    ims = MainActivity.this.getContentResolver().openInputStream(outputFileUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageBitmap = BitmapFactory.decodeStream(ims);
            } else {
//                imageBitmap = decodeSampledBitmapFromUri(outputFileUri, addkyc.getWidth(), addkyc.getHeight(), Env.currentActivity);
            }
            if (imageBitmap == null) {
            } else {
                /*if (imageFile != null) {
                    selectedImagePath = Util.getRealPathFromURI(MainActivity.this, outputFileUri);
                    imageBitmap = Util.rotateImageBitmap(selectedImagePath, imageBitmap);
                }*/
                if (imageBitmap != null) {
                    imageFile = compressImage(MainActivity.this, imageFile);
                    if (button_click == 1) {
                        imageFile = new File(selectedImagePath);
                        tv_id_doc.setText("");
                        tv_id_doc.setBackground(new BitmapDrawable(getResources(), imageBitmap));
                        tv_id_doc.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success, 0);
                    } else if (button_click == 2) {
                        tv_tin_doc.setText("");
                        tv_tin_doc.setBackground(new BitmapDrawable(getResources(), imageBitmap));
                        tv_tin_doc.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success, 0);
                        imageFile1 = new File(selectedImagePath);
                    } else if (button_click == 3) {
                        tv_license_doc.setText("");
                        tv_license_doc.setBackground(new BitmapDrawable(getResources(), imageBitmap));
                        tv_license_doc.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success, 0);
                        imageFile2 = new File(selectedImagePath);
                    } else if (button_click == 4) {
                        tv_permit_1_doc.setText("");
                        tv_permit_1_doc.setBackground(new BitmapDrawable(getResources(), imageBitmap));
                        tv_permit_1_doc.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success, 0);
                        imageFile3 = new File(selectedImagePath);
                    } else if (button_click == 5) {
                        tv_agreement_doc.setText("");
                        tv_agreement_doc.setBackground(new BitmapDrawable(getResources(), imageBitmap));
                        tv_agreement_doc.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success, 0);
                        imageFile4 = new File(selectedImagePath);
                    }
//                    imageFile = new File(selectedImagePath);
                }
            }
        } /*else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }*/
    }

    private void submitForm() {
        try {

            if (Util.hasInternet(this)) {
                Util.showProDialog(MainActivity.this);
                mobile_number = et_mobile.getText().toString().trim();
                String mobile1 = mobile_number.replaceAll("[() ]", "");
                mobile1 = mobile1.startsWith("0") ? mobile1.replaceFirst("0", "") : mobile1;


                String contact_number1 = et_contactnumber.getText().toString().trim();
                String mobile11 = contact_number1.replaceAll("[() ]", "");
                mobile11 = mobile11.startsWith("0") ? mobile11.replaceFirst("0", "") : mobile11;


                JSONObject jsonObject = new JSONObject();
                HashMap<String, RequestBody> myHashMap = new HashMap<>();
                try {
                    jsonObject.put("mobile_number", mobile1);
                    jsonObject.put("city_id", citylist.get(citypos - 14).getId());
                    jsonObject.put("bank_id", String.valueOf(bank_id));
                    jsonObject.put("pin", et_pin.getText().toString().trim());
                    jsonObject.put("name", et_name.getText().toString().trim());
                    jsonObject.put("device_id", device_id);
                    jsonObject.put("device_type", "android");
                    jsonObject.put("contact_person", et_contact_person.getText().toString());
                    jsonObject.put("contact_number", mobile11);
                    jsonObject.put("location", et_location.getText().toString());
                    jsonObject.put("beneficiary_name", et_benificary_name.getText().toString());
                    jsonObject.put("bank_account_number", et_account_number.getText().toString());
                    jsonObject.put("email", et_email.getText().toString());
                    jsonObject.put("created_by", Config.getUserId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                RequestBody part_data = RequestBody.create(MediaType.parse("multipart/form-data"), jsonObject.toString());
                myHashMap.put("data", part_data);
                MultipartBody.Part doc_image = null;
                MultipartBody.Part doc_image1 = null;
                MultipartBody.Part doc_image2 = null;
                MultipartBody.Part doc_image3 = null;
                MultipartBody.Part doc_image4 = null;
                if (imageFile == null) {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                    doc_image = MultipartBody.Part.createFormData("image", "", requestFile);
                } else {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
                    doc_image = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);
                }
                if (imageFile1 == null) {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                    doc_image1 = MultipartBody.Part.createFormData("image", "", requestFile);
                } else {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile1);
                    doc_image1 = MultipartBody.Part.createFormData("image", imageFile1.getName(), requestFile);
                }


                if (imageFile2 == null) {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                    doc_image2 = MultipartBody.Part.createFormData("image", "", requestFile);
                } else {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile2);
                    doc_image2 = MultipartBody.Part.createFormData("image", imageFile2.getName(), requestFile);
                }
                if (imageFile3 == null) {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                    doc_image3 = MultipartBody.Part.createFormData("image", "", requestFile);
                } else {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile3);
                    doc_image3 = MultipartBody.Part.createFormData("image", imageFile3.getName(), requestFile);
                }
                if (imageFile4 == null) {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                    doc_image4 = MultipartBody.Part.createFormData("image", "", requestFile);
                } else {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile4);
                    doc_image4 = MultipartBody.Part.createFormData("image", imageFile4.getName(), requestFile);
                }


                ApiService apiService = App.getInitialClient().create(ApiService.class);
                Call<RegistrationResponse> call = apiService.wakala_regestration(myHashMap, doc_image, doc_image1, doc_image2, doc_image3, doc_image4);
                call.enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        if (response.body() != null) {
                            registrationResponse = response.body();
                            if (registrationResponse.getResponse().getStatus()) {
                                name = registrationResponse.getResponse().getData().get(0).getName();
                                mobile = registrationResponse.getResponse().getData().get(0).getMobileNumber();
                                email = registrationResponse.getResponse().getData().get(0).getEmail();
                                bank = registrationResponse.getResponse().getData().get(0).getBankName();
                                bank_account_number = registrationResponse.getResponse().getData().get(0).getBankAccountNumber();
                                benificary_name = registrationResponse.getResponse().getData().get(0).getBeneficiaryName();
                                city = cityname;
                                pin = registrationResponse.getResponse().getData().get(0).getPin();
                                contact_person = registrationResponse.getResponse().getData().get(0).getContactPerson();
                                contact_number = registrationResponse.getResponse().getData().get(0).getContactNumber();
                                location = registrationResponse.getResponse().getData().get(0).getLocation();
                                SaveData st = new SaveData();
                                st.execute();

                            } else {
                                Util.dismissProDialog();
                                Util.showErrorSnackBar(constraintLayout, registrationResponse.getResponse().getMessage(), MainActivity.this);
                                if (registrationResponse.getResponse().getUnderMaintenance().equalsIgnoreCase("1")) {
//                                                Util.doLogout(constraintLayout, MainActivity.this, MainActivity.this);
                                } else if (registrationResponse.getResponse().getIsDeactivate().equalsIgnoreCase("1")) {
////                                                Util.doLogout(constraintLayout, MainActivity.this, MainActivity.this);
                                } else if (registrationResponse.getResponse().getSflag().equalsIgnoreCase("1")) {
                                    Util.doSFlagLogout(constraintLayout, MainActivity.this, MainActivity.this);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        Log.e("fail", t.toString());
                        Log.e("gd", t.getMessage());
                        Util.dismissProDialog();
                    }
                });
            } else {
                Util.showErrorSnackBar(constraintLayout, getResources().getString(R.string.no_internet), MainActivity.this);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Boolean validateform() {
        if (et_name.getText().toString().isEmpty()) {
            Util.showErrorSnackBar(constraintLayout, "Please enter name", this);
            return false;
        } else if (et_mobile.getText().toString().isEmpty()) {
            Util.showErrorSnackBar(constraintLayout, "Please enter mobile number", this);
            return false;
        } else if (et_mobile.getText().toString().length() < 12) {
            Util.showErrorSnackBar(constraintLayout, "Please enter valid mobile number", this);
            return false;
        } else if (et_bank.getSelectedItemPosition() == 0) {
            Util.showErrorSnackBar(constraintLayout, "Please select bank", this);
            return false;
        } else if (et_benificary_name.getText().toString().isEmpty()) {
            Util.showErrorSnackBar(constraintLayout, "Please enter beneficiary name", this);
            return false;
        } else if (et_account_number.getText().toString().isEmpty()) {
            Util.showErrorSnackBar(constraintLayout, "Please enter account number", this);
            return false;
        } else if (et_city.getSelectedItemPosition() == 0) {
            Util.showErrorSnackBar(constraintLayout, "Please select city", this);
            return false;
        } else if (et_contact_person.getText().toString().isEmpty()) {
            Util.showErrorSnackBar(constraintLayout, "Please enter contact person", this);
            return false;
        } else if (et_contactnumber.getText().toString().isEmpty()) {
            Util.showErrorSnackBar(constraintLayout, "Please enter contact number", this);
            return false;
        } else if (et_contactnumber.getText().toString().length() < 12) {
            Util.showErrorSnackBar(constraintLayout, "Please enter valid contact number", this);
            return false;
        } else if (et_location.getText().toString().isEmpty()) {
            Util.showErrorSnackBar(constraintLayout, "Please enter location", this);
            return false;
        }
        if (TextUtils.isEmpty(et_pin.getText().toString().trim())) {
            Util.showErrorSnackBar(constraintLayout, "Please enter pin", this);
            return false;
        } else if (et_pin.getText().toString().trim().length() < 4) {
            Util.showErrorSnackBar(constraintLayout, "Please enter 4 digit pin", this);
            return false;

        } else if (TextUtils.isEmpty(et_cpin.getText().toString().trim())) {
            Util.showErrorSnackBar(constraintLayout, "Please enter confrim pin", this);
            return false;
        } else if (et_cpin.getText().toString().trim().length() < 4) {
            Util.showErrorSnackBar(constraintLayout, "Please enter 4 digit confrim pin", this);
            return false;
        } else if (!(et_pin.getText().toString().trim().equals(et_cpin.getText().toString().trim()))) {
            Util.showErrorSnackBar(constraintLayout, "Pin do not match", this);
            return false;

        } else if (!et_email.getText().toString().isEmpty()) {
            if (!et_email.getText().toString().matches(email_patern)) {
                Util.showErrorSnackBar(constraintLayout, "Please enter valid email ID", this);
                return false;
            }
            return true;
        }
        return true;
    }

    private void getBankList() {
        try {
            if (Util.hasInternet(this)) {
//                Util.showProDialog(MainActivity.this);
                ApiService apiService = App.getInitialClient().create(ApiService.class);
                Call<BankResponse> call = apiService.bank_list();
                call.enqueue(new Callback<BankResponse>() {
                    @Override
                    public void onResponse(Call<BankResponse> call, Response<BankResponse> response) {
                        if (response.body() != null) {
                            bankResponse = response.body();
                            if (bankResponse.getResponse().getStatus()) {
//                                Util.dismissProDialog();
                                bank_id = bankResponse.getResponse().getData().get(0).getId();
                                bankname = bankResponse.getResponse().getData().get(0).getBankName();
                                BankResponse.Response.Datum datum = new BankResponse.Response.Datum("fdg", "Select Bank", "Select Bank");
                                banklist = new ArrayList<>();
                                banklist.add(datum);
                                banklist.addAll(bankResponse.getResponse().getData());
                                CustomAdapter aa = new CustomAdapter(MainActivity.this, banklist);
                                et_bank.setAdapter(aa);

                            } else {

//                                Util.dismissProDialog();
                                Util.showErrorSnackBar(constraintLayout, bankResponse.getResponse().getMessage(), MainActivity.this);
                                if (bankResponse.getResponse().getUnderMaintenance().equalsIgnoreCase("1")) {
//                                                Util.doLogout(constraintLayout, MainActivity.this, MainActivity.this);

                                } else if (bankResponse.getResponse().getIsDeactivate().equalsIgnoreCase("1")) {
//                                                Util.doLogout(constraintLayout, MainActivity.this, MainActivity.this);
                                } else if (bankResponse.getResponse().getSflag().equalsIgnoreCase("1")) {
                                    Util.doSFlagLogout(constraintLayout, MainActivity.this, MainActivity.this);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BankResponse> call, Throwable t) {
                        Log.e("fail", t.toString());
                        Util.dismissProDialog();
                        Util.showErrorSnackBar(constraintLayout, bankResponse.getResponse().getMessage(), MainActivity.this);

                    }
                });
            } else {
                Util.showErrorSnackBar(constraintLayout, getResources().getString(R.string.no_internet), MainActivity.this);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCityList() {

        try {
            if (Util.hasInternet(this)) {
//                Util.showProDialog(MainActivity.this);
                ApiService apiService = App.getInitialClient().create(ApiService.class);
                Call<CityResponse> call = apiService.city_list();
                call.enqueue(new Callback<CityResponse>() {
                    @Override
                    public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                        if (response.body() != null) {
                            cityResponse = response.body();
                            if (cityResponse.getResponse().getStatus()) {
//                                Util.dismissProDialog();
                                city_id = cityResponse.getResponse().getData().get(0).getId();
                                cityname = cityResponse.getResponse().getData().get(0).getCityName();
                                citylist = new ArrayList<>();
                                CityResponse.Response.Datum datum = new CityResponse.Response.Datum("bjkj", "Select City");
                                citylist.add(datum);
                                citylist.addAll(cityResponse.getResponse().getData());
                                CityCustomAdapter aa1 = new CityCustomAdapter(MainActivity.this, citylist);
                                et_city.setAdapter(aa1);
                            } else {

//                                Util.dismissProDialog();
                                Util.showErrorSnackBar(constraintLayout, cityResponse.getResponse().getMessage(), MainActivity.this);
                                if (bankResponse.getResponse().getUnderMaintenance().equalsIgnoreCase("1")) {
//                                                Util.doLogout(constraintLayout, MainActivity.this, MainActivity.this);

                                } else if (bankResponse.getResponse().getIsDeactivate().equalsIgnoreCase("1")) {
//                                                Util.doLogout(constraintLayout, MainActivity.this, MainActivity.this);
                                } else if (bankResponse.getResponse().getSflag().equalsIgnoreCase("1")) {
                                    Util.doSFlagLogout(constraintLayout, MainActivity.this, MainActivity.this);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CityResponse> call, Throwable t) {
                        Log.e("fail", t.toString());
                        Util.dismissProDialog();
                        Util.showErrorSnackBar(constraintLayout, cityResponse.getResponse().getMessage(), MainActivity.this);

                    }
                });
            } else {
                Util.showErrorSnackBar(constraintLayout, getResources().getString(R.string.no_internet), MainActivity.this);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alertDailog() {
        if (validateform()) {
            String email;
            if (!et_email.getText().toString().isEmpty())
                email = "\nEmail ID: " + et_email.getText().toString();
            else
                email = "";

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("aaa").setTitle("Confrim");
            builder.setMessage("Name: " + et_name.getText().toString().trim() +
                    "\nMobile: " + et_mobile.getText().toString().trim() +
                    email +
                    "\nBank name: " + bankname +
                    "\nBank account number: " + et_account_number.getText().toString().trim() +
                    "\nBeneficiary name: " + et_benificary_name.getText().toString().trim() +
                    "\nCity: " + citylist.get(citypos - 14).getCityName() +
                    "\nPin: " + et_pin.getText().toString().trim() +
                    "\nContatct person: " + et_contact_person.getText().toString().trim() +
                    "\nContatct number: " + et_contactnumber.getText().toString().trim() +
                    "\nLocation: " + et_location.getText().toString().trim())
                    .setCancelable(false)
                    .setPositiveButton("Continue ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            submitForm();
                            dialog.dismiss();

                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }

    }

    private void askPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                    }, 1);
        } else {
            showPopmenu();
        }
    }

    private void showPopmenu() {
        dialog = new Dialog(MainActivity.this, R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.cameradialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans_black)));
        mCamerabtn = (Button) dialog.findViewById(R.id.cameradialogbtn);
        mGallerybtn = (Button) dialog.findViewById(R.id.gallerydialogbtn);
        btnCancell = (Button) dialog.findViewById(R.id.canceldialogbtn);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        mCamerabtn.setOnClickListener(this);
        mGallerybtn.setOnClickListener(this);
        btnCancell.setOnClickListener(this);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this, RegistarionFormListActivity.class));
    }

    private class CustomAdapter extends BaseAdapter implements SpinnerAdapter {

        List<BankResponse.Response.Datum> banklist;
        Context context;


        public CustomAdapter(Context context, List<BankResponse.Response.Datum> company) {
            this.banklist = company;
            this.context = context;
        }

        @Override
        public int getCount() {
            return banklist.size();
        }

        @Override
        public BankResponse.Response.Datum getItem(int position) {
            return banklist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean isEnabled(int position) {
            if (position == 0) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(context, R.layout.company_main, null);
            TextView textView = (TextView) view.findViewById(R.id.main);


            if (position == 0) {
                // Set the hint text color gray
                textView.setTextColor(Color.GRAY);
                textView.setText(banklist.get(position).getBankName());
            } else {
                textView.setTextColor(Color.BLACK);
                textView.setText(banklist.get(position).getBankName());
                bank_id = banklist.get(position).getId();
                bankname = banklist.get(position).getBankName();
            }
            Log.e("ddfgdgf-----------", bankname);
            return textView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {

            View view;
            view = View.inflate(context, R.layout.company_dropdown, null);
            final TextView textView = (TextView) view.findViewById(R.id.dropdown);
            textView.setText(banklist.get(position).getBankName());
            return view;
        }
    }

    private class CityCustomAdapter extends BaseAdapter implements SpinnerAdapter {

        List<CityResponse.Response.Datum> citylist;
        Context context;


        public CityCustomAdapter(Context context, List<CityResponse.Response.Datum> company) {
            this.citylist = company;
            this.context = context;
        }

        @Override
        public int getCount() {
            return citylist.size();
        }

        @Override
        public CityResponse.Response.Datum getItem(int position) {
            return citylist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean isEnabled(int position) {
            if (position == 0) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(context, R.layout.cityname, null);
            tv = (TextView) view.findViewById(R.id.main);
            if (position == 0) {
                // Set the hint text color gray
                tv.setTextColor(Color.GRAY);
                tv.setText(citylist.get(position).getCityName());
            } else {
                tv.setTextColor(Color.BLACK);
                tv.setText(citylist.get(position).getCityName());
                city_id = citylist.get(position).getId();
                cityname = citylist.get(position).getCityName();
                citypos = position;

            }
            Log.e("ddfgdgf-----------", cityname);
            return tv;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {

            View view;
            view = View.inflate(context, R.layout.citydropdown, null);
            final TextView textView = (TextView) view.findViewById(R.id.dropdown);
            textView.setText(citylist.get(position).getCityName());
            return view;
        }
    }

    class SaveData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            RegistrationDatabase.getInstance(getApplicationContext()).getRegistrationDao()
                    .insert(new RegistartionData(name, email, mobile, bank, bank_account_number, benificary_name, citylist.get(citypos - 14).getId(), pin, contact_person, contact_number, location, new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()).format(Calendar.getInstance().getTime())));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Util.dismissProDialog();
            finish();
            startActivity(new Intent(MainActivity.this, RegistarionFormListActivity.class));
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        }
    }

}
