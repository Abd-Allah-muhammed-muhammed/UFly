package com.abdallah.ufly.retrofit;

import com.abdallah.ufly.model.addTrip.AddTripResponse;
import com.abdallah.ufly.model.book.BookModelResponse;
import com.abdallah.ufly.model.cashPay.CashPay;
import com.abdallah.ufly.model.company_info.CompanyAccountResponse;
import com.abdallah.ufly.model.delet_trip.DelelteMyTripResponse;
import com.abdallah.ufly.model.emailvalidetion.EmailVerificationModel;
import com.abdallah.ufly.model.login.LoginResponse;
import com.abdallah.ufly.model.login_company.LoginCompany;
import com.abdallah.ufly.model.my_info.MyInfoResponse;
import com.abdallah.ufly.model.my_trip.MyTripsResponse;
import com.abdallah.ufly.model.passenger_booked.PassengerBookedResponse;
import com.abdallah.ufly.model.registration.RegistarResponse;
import com.abdallah.ufly.model.trips.TripsResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.Nullable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegistarResponse> singUp(@Field("email") String email,
                                  @Field("fullname") String fullname,
                                  @Field("password") String password,
                                  @Field("address") String address,
                                  @Field("phone") String phone);


    @FormUrlEncoded
    @POST("login.php")
    Observable<LoginResponse> login(@Field("email") String email,
                                    @Field("password") String password);




    @GET("trips.php")
    Observable<List<TripsResponse>> getAllTrips(@Nullable @Query("query")String query );


    @GET("my_complet_trips.php")
    Observable<MyTripsResponse> getMyCompleteTrips(@Query("uui_id") String uui_id);


    @GET("my_panding_trips.php")
    Observable<MyTripsResponse> getMyPandingTrips(@Query("uui_id") String uui_id);

 @GET("my_old_trips.php")
    Observable<MyTripsResponse> getMyoldTrips(@Query("uui_id") String uui_id);


    @FormUrlEncoded
    @POST("book.php")
    Observable<BookModelResponse> book(@Field("id_trip") String id_trip
            , @Field("uui_id") String uui_id,
                                       @Field("id_payment") String id_payment,
                                       @Field("price") String price,
                                       @Field("number") String number,
                                       @Field("id_company") String id_comp);

    @FormUrlEncoded
    @POST("delete_trip.php")
    Single<DelelteMyTripResponse> cancleMyTrip(@Field("uui_id") String uui_id,
                                               @Field("trip_id") int trip_id);
 @FormUrlEncoded
    @POST("setIsPaid.php")
    Single<CashPay> cashPay(@Field("uui_id") String uui_id,
                            @Field("is_paid") int is_paid);

    @FormUrlEncoded
    @POST("my_info.php")
    Observable<MyInfoResponse> getMyinfo(@Field("uui_id") String uui_id);


    @FormUrlEncoded
    @POST("login_company.php")
    Observable<LoginCompany> loginCompany(@Field("company_id") String company_id);


    @GET("my_trip_company.php")
    Observable<List<TripsResponse>> getMyTripsCompany(@Query("id_company") String id_company);


    @GET("booksCompany.php")
    Observable<PassengerBookedResponse> getPassengerBooked(@Query("id_company") String id_company, @Query("id_trip") int id_trip);


    @Multipart
    @POST("add_trip.php")
    Observable<AddTripResponse> addTrip(@Part("departure") RequestBody departure,
                                        @Part("arrival") RequestBody arrival
            , @Part("date_from") RequestBody date_from,
                                        @Part("date_until") RequestBody date_until,
                                        @Part("passengers") RequestBody passengers,
                                        @Part("price") RequestBody price,
                                        @Part("description") RequestBody description,
                                        @Part("company_id") RequestBody company_id,
                                        @Part("includes") RequestBody includes,
                                        @Part("time_in") RequestBody time_in,
                                        @Part("time_out") RequestBody time_out,
                                        @Part MultipartBody.Part image);





    @GET("my_info_company.php")
    Observable<CompanyAccountResponse> getMyinfoCompany(@Query("company_id") String company_id);



    @FormUrlEncoded
    @POST("valid_mail.php")
    Single<EmailVerificationModel> checkCodeEmail(@Field("id_mail_valid") String id_mail_valid);


    @FormUrlEncoded
    @POST("change_mail.php")
    Single<EmailVerificationModel> resendAndUpdateEmail(@Field("uui_id") String uui_id,
                                               @Field("email") String email);


}
