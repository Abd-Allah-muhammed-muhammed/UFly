package com.abdallah.ufly.retrofit;

import com.abdallah.ufly.model.book.BookModelResponse;
import com.abdallah.ufly.model.delet_trip.DelelteMyTripResponse;
import com.abdallah.ufly.model.login.LoginResponse;
import com.abdallah.ufly.model.login_company.LoginCompany;
import com.abdallah.ufly.model.my_info.MyInfoResponse;
import com.abdallah.ufly.model.my_trip.MyTripResponse;
import com.abdallah.ufly.model.registration.RegistarResponse;
import com.abdallah.ufly.model.trips.TripsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegistarResponse> singUp(@Field("email") String email,
                                  @Field("fullname") String fullname,
                                  @Field("password") String password,
                                  @Field("address") String address,
                                  @Field("phone") String phone);
//
//    @FormUrlEncoded
//    @POST("includes_by_id.php")
//    Call<IncludesResponse> getIncludesById(@Field("id_trip") String id_trip);


    @FormUrlEncoded
    @POST("login.php")
    Observable<LoginResponse> login(@Field("email") String email,
                                    @Field("password") String password);


    @GET("trips.php")
    Observable<List<TripsResponse>> getAllTrips();


    @GET("my_trips.php")
    Observable<MyTripResponse> getMyTrips(@Query("uui_id") String uui_id);


    @FormUrlEncoded
    @POST("book.php")
    Observable<BookModelResponse> book(@Field("id_trip") String id_trip
            , @Field("uui_id") String uui_id,
                                       @Field("id_payment") String id_payment,
                                       @Field("price") String price,
                                       @Field("number") String number);

    @FormUrlEncoded
    @POST("delete_trip.php")
    Observable<DelelteMyTripResponse> cancleMyTrip(@Field("uui_id") String uui_id);

    @FormUrlEncoded
    @POST("my_info.php")
    Observable<MyInfoResponse> getMyinfo(@Field("uui_id") String uui_id);


    @FormUrlEncoded
    @POST("login_company.php")
    Observable<LoginCompany> loginCompany(@Field("company_id") String company_id);

}
