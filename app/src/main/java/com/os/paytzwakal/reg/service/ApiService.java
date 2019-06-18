package com.os.paytzwakal.reg.service;

import com.os.paytzwakal.reg.pojo.BankResponse;
import com.os.paytzwakal.reg.pojo.CityResponse;
import com.os.paytzwakal.reg.pojo.RegistrationResponse;
import com.os.paytzwakal.reg.pojo.UserResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface ApiService {

    @Headers(
            {"Content-Type: application/json", "REMENBER-TOKEN: awsderegtejkdfhsdfsjfd"})
    @POST("freelancer_login")
    Call<UserResponse> login_call(@Body Map<String, Object> map);


    @Headers({"REMENBER-TOKEN: awsderegtejkdfhsdfsjfd"})
    @Multipart
    @POST("wakala_regestration")
    Call<RegistrationResponse> wakala_regestration(@PartMap Map<String, RequestBody> comRequest,
                                                   @Part MultipartBody.Part image,
                                                   @Part MultipartBody.Part image1,
                                                   @Part MultipartBody.Part image2,
                                                   @Part MultipartBody.Part image3,
                                                   @Part MultipartBody.Part image4);

    @Headers(
            {"Content-Type: application/json", "REMENBER-TOKEN: awsderegtejkdfhsdfsjfd"})
    @POST("city_list")
    Call<CityResponse> city_list();

    @Headers(
            {"Content-Type: application/json", "REMENBER-TOKEN: awsderegtejkdfhsdfsjfd"})
    @GET("bank_list")
    Call<BankResponse> bank_list();

}
