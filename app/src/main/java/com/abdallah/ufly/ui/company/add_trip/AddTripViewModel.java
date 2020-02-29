package com.abdallah.ufly.ui.company.add_trip;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.addTrip.ModelAddTrip;
import com.abdallah.ufly.repository.RepositoryAddTrip;
import com.abdallah.ufly.ui.company.book_company.BooksCompanyFragment;
import com.abdallah.ufly.ui.company.hom.HomCompanyFragment;
import com.muddzdev.styleabletoast.StyleableToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class AddTripViewModel extends ViewModel {


    public MutableLiveData<Boolean> focus;
    public MutableLiveData<String> textBtn;
    public MutableLiveData<Integer> passenger_visibility;


    //
    //
    //
    //
    //
    public MutableLiveData<String> trip_desc;
    public MutableLiveData<String> trip_from;
    public MutableLiveData<String> trip_to;
    public MutableLiveData<String> trip_price;
    public MutableLiveData<String> trip_includes;
    public MutableLiveData<String> trip_timeIn;
    public MutableLiveData<String> trip_timeout;

 public MutableLiveData<String> trip_dateUntil;
    public MutableLiveData<String> trip_datFrom;
    public MutableLiveData<String> trip_passengers;
    private int trip_id;

    private String image_path_file ;




    private int id;
    ModelAddTrip modelAddTrip;

    private PrefManager prefManager;
    RepositoryAddTrip repository;

    final Calendar myCalendar;

    Bundle bundle ;

    ProgressBar progressBar ;
    ImageView image_trip ;
    public AddTripViewModel(ProgressBar progressBar , ImageView image_trip) {

        this.image_trip = image_trip;
        this.progressBar = progressBar;
        bundle = new Bundle();
        modelAddTrip = new ModelAddTrip();

        repository =  RepositoryAddTrip.getInstance();
        myCalendar = Calendar.getInstance();
        focus = new MutableLiveData<>();
        textBtn = new MutableLiveData<>();
        trip_timeIn = new MutableLiveData<>();
        trip_timeout = new MutableLiveData<>();


        //init text Desc Fragment
        trip_desc = new MutableLiveData<>();
        trip_from = new MutableLiveData<>();
        trip_to = new MutableLiveData<>();
        trip_price = new MutableLiveData<>();
        trip_includes = new MutableLiveData<>();

        trip_datFrom = new MutableLiveData<>();
        trip_dateUntil= new MutableLiveData<>();
        trip_passengers = new MutableLiveData<>();
        passenger_visibility = new MutableLiveData<>();
        trip_id = 0;


    }


    public void setDesc(int id, int trip_id, String trip_desc, String trip_from, String trip_to, String price, String includes, String dateFrome
            , String dateTo, String passengers, Context context, String time_in, String time_out) {
        this.id = id;

        this.trip_id = trip_id;


        if (id==2){

            // description fragment
            //set data to the view
            this.trip_desc.setValue(trip_desc);
            this.trip_from.setValue(trip_from);
            this.trip_to.setValue(trip_to);
            this.trip_price.setValue(price);
            this.trip_includes.setValue(includes);
            this.trip_datFrom.setValue(dateFrome);
            this.trip_dateUntil.setValue(dateTo);
            this.trip_passengers.setValue(passengers);
            this.trip_timeIn.setValue(time_in);
            this.trip_timeout.setValue(time_out);

            focus.setValue(false);

            textBtn.setValue(context.getString(R.string.don));



        }else {

            textBtn.setValue(context.getString(R.string.add_trip));
            passenger_visibility.setValue(8);
            focus.setValue(true);

        }


    }


    public void setFrom(CharSequence s, int start, int before, int count) {

        modelAddTrip.setFrom(String.valueOf(s).toUpperCase());


    }

    public void setTo(CharSequence s, int start, int before, int count) {


        modelAddTrip.setTo(String.valueOf(s).toUpperCase());


    }

    public void setIncludes(CharSequence s, int start, int before, int count) {

        modelAddTrip.setIncludse(String.valueOf(s));


    }

    public void setDescription(CharSequence s, int start, int before, int count) {

        modelAddTrip.setDescription(String.valueOf(s));


    }

    public void setPrice(CharSequence s, int start, int before, int count) {


        modelAddTrip.setPrice(String.valueOf(s));

    }

    public void setPassengers(CharSequence s, int start, int before, int count) {


        modelAddTrip.setPassengers(String.valueOf(s));

    }

    public void dateFrom(View view) {

        if (id!=2){
            DatePickerDialog.OnDateSetListener date = openCalenderFrom((TextView) view);
            new DatePickerDialog(view.getContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        }


    }


    public void dateUntil(View view) {


        if (id!=2){

            DatePickerDialog.OnDateSetListener date = openCalenderUntil((TextView) view);
            new DatePickerDialog(view.getContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        }




    }

    private DatePickerDialog.OnDateSetListener openCalenderUntil(final TextView textView) {

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelUntil(textView);
            }

        };

        return date;


    }


    private DatePickerDialog.OnDateSetListener openCalenderFrom(final TextView textView) {


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelFrom(textView);
            }

        };

        return date;


    }


    private void updateLabelFrom(TextView view) {

        Context mContext = view.getContext();
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);



        Date time = myCalendar.getTime();

        long prevDay = System.currentTimeMillis();
        Date prev = new Date(prevDay);


        if (time.after(prev)){
            view.setText(sdf.format(myCalendar.getTime()));

            modelAddTrip.setDatFrom(sdf.format(myCalendar.getTime()));

        }else {

            view.setText("");

            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(mContext.getString(R.string.paytabs_error),mContext.getString(R.string.wrong_date),R.drawable.ic_sad_24dp);
            generalDialogFragment.show(((FragmentActivity)mContext).getSupportFragmentManager(),"dialog");
        }


    }


    private void updateLabelUntil(TextView view) {


        Context mContext = view.getContext();
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Date time = myCalendar.getTime();

        long prevDay = System.currentTimeMillis();
        Date prev = new Date(prevDay);


        if (time.after(prev)){
            view.setText(sdf.format(myCalendar.getTime()));

            modelAddTrip.setDatUntil(sdf.format(myCalendar.getTime()));

        }else {

            view.setText("");

            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(mContext.getString(R.string.paytabs_error),mContext.getString(R.string.wrong_date),R.drawable.ic_sad_24dp);
            generalDialogFragment.show(((FragmentActivity)mContext).getSupportFragmentManager(),"dialog");
        }





    }

    public void addTrip(View view) {




        if (id==2){


            replace(new HomCompanyFragment(), R.id.container_home_company, ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction(), view.getContext().getString(R.string.tag_hom_company));


        }else {


            Context mContext = view.getContext();

            if (!isNetworkAvailable(mContext)){


                GeneralDialogFragment generalDialogFragment =
                        GeneralDialogFragment.newInstance(mContext.getString(R.string.no_intrnet),mContext.getString(R.string.paytabs_err_no_internet),R.drawable.ic_no_internet);
                generalDialogFragment.show(((FragmentActivity)mContext).getSupportFragmentManager(),"dialog");

            }else {

                if (isEmpty(modelAddTrip.getFrom())) {
                    setToastError("please Enter From", view);

                } else if (isEmpty(modelAddTrip.getTo())) {

                    setToastError("please Enter To", view);
                } else if (isEmpty(modelAddTrip.getDatFrom())) {

                    setToastError("please Enter Date From", view);
                } else if (isEmpty(modelAddTrip.getDatUntil())) {

                    setToastError("please Enter Date Until", view);
                } else if (isEmpty(modelAddTrip.getDescription())) {

                    setToastError("please Enter the Description", view);
                } else if (isEmpty(modelAddTrip.getPrice())) {

                    setToastError("please Enter The price", view);
                } else if (isEmpty(modelAddTrip.getPassengers())) {

                    setToastError("please Enter the number of passengers", view);
                } else {
                    prefManager = new PrefManager(view.getContext());
                    String id_company = prefManager.getID_Company();
                    repository.addTrip(modelAddTrip, id_company, (Button) view,progressBar , image_trip , image_path_file );

                }




            }




        }




    }


    private boolean isEmpty(String s) {

        return TextUtils.isEmpty(s);
    }


    private void setToastError(String msg, View view) {

        StyleableToast.makeText(view.getContext(), msg, Toast.LENGTH_LONG, R.style.error_company).show();


    }


    public  void passengerInfo (View  view){

        BooksCompanyFragment fragment  = new BooksCompanyFragment();

        bundle.putInt("trip_id",trip_id);
        fragment.setArguments(bundle);

        replace(fragment, R.id.container_home_company, ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction(), view.getContext().getString(R.string.tag_books_comp));


    }


    public void timeUntil (View view){




        if (id!=2) {
            final TextView  texttime = (TextView) view;

            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    texttime.setText( selectedHour + ":" + selectedMinute);
                    modelAddTrip.setTimeOut( selectedHour + ":" + selectedMinute);

                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();


        }



    }


    public void timeIn ( View   view){

        if (id!=2){

            final TextView  texttime = (TextView) view;

            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    texttime.setText( selectedHour + ":" + selectedMinute);
                    modelAddTrip.setTimeIn( selectedHour + ":" + selectedMinute);
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        }


    }





    public void getPath(String path) {

        this.image_path_file = path;
    }
}


