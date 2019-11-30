package com.abdallah.ufly.retrofit;

import com.abdallah.ufly.model.SignUp;
import com.abdallah.ufly.model.login.LoginResponse;
import com.abdallah.ufly.model.registration.RegistarResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegistarResponse> singUp(@Field("email")String email,
                                  @Field("fullname") String fullname,
                                  @Field("password")String password,
                                  @Field("address")String address,
                                  @Field("phone")String phone);
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("email")String email,
                              @Field("password")String password);

}
