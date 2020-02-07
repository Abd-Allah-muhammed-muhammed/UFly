package com.abdallah.ufly.retrofit;

import com.abdallah.ufly.model.addTrip.AddTripResponse;
import com.abdallah.ufly.model.book.BookModelResponse;
import com.abdallah.ufly.model.cashPay.CashPay;
import com.abdallah.ufly.model.company_info.CompanyAccountResponse;
import com.abdallah.ufly.model.delet_trip.DelelteMyTripResponse;
import com.abdallah.ufly.model.login.LoginResponse;
import com.abdallah.ufly.model.login_company.LoginCompany;
import com.abdallah.ufly.model.my_info.MyInfoResponse;
import com.abdallah.ufly.model.my_trip.MyTripResponse;
import com.abdallah.ufly.model.passenger_booked.PassengerBookedResponse;
import com.abdallah.ufly.model.registration.RegistarResponse;
import com.abdallah.ufly.model.trips.TripsResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
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

    @FormUrlEncoded
    @POST("add_trip.php")
    Observable<AddTripResponse> addTrip(@Field("departure") String departure,
                                        @Field("arrival") String arrival
            , @Field("date_from") String date_from,
                                        @Field("date_until") String date_until,
                                        @Field("passengers") String passengers,
                                        @Field("price") String price,
                                        @Field("description") String description,
                                        @Field("company_id") String company_id,
                                        @Field("includes") String includes,
                                        @Field("time_in") String time_in,
                                        @Field("time_out") String time_out);





    @GET("my_info_company.php")
    Observable<CompanyAccountResponse> getMyinfoCompany(@Query("company_id") String company_id);

}
