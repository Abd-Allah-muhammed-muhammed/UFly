package com.abdallah.ufly.ui.company.add_trip;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.addTrip.ModelAddTrip;
import com.abdallah.ufly.repository.RepositoryAddTrip;
import com.abdallah.ufly.ui.company.hom.HomCompanyFragment;
import com.muddzdev.styleabletoast.StyleableToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class AddTripViewModel extends ViewModel {


    public MutableLiveData<Integer> progress;
    public MutableLiveData<Boolean> focus;
    public MutableLiveData<String> textBtn;


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

 public MutableLiveData<String> trip_dateUntil;
    public MutableLiveData<String> trip_datFrom;
    public MutableLiveData<String> trip_passengers;




    private int id;
    ModelAddTrip modelAddTrip;

    private PrefManager prefManager;
    RepositoryAddTrip repository;

    final Calendar myCalendar;

    public AddTripViewModel() {

        modelAddTrip = new ModelAddTrip();
        progress = new MutableLiveData<>();
        progress.setValue(8);
        repository = new RepositoryAddTrip();
        myCalendar = Calendar.getInstance();
        focus = new MutableLiveData<>();
        textBtn = new MutableLiveData<>();


        //init text Desc Fragment
        trip_desc = new MutableLiveData<>();
        trip_from = new MutableLiveData<>();
        trip_to = new MutableLiveData<>();
        trip_price = new MutableLiveData<>();
        trip_includes = new MutableLiveData<>();

        trip_datFrom = new MutableLiveData<>();
        trip_dateUntil= new MutableLiveData<>();
        trip_passengers = new MutableLiveData<>();


    }


    public void setDesc(int id, int trip_id,String trip_desc, String trip_from, String trip_to, String price,String includes ,  String dateFrome
    ,String dateTo , String passengers) {
        this.id = id;


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

            focus.setValue(false);
            textBtn.setValue("Done");



        }else {

            textBtn.setValue("Add Trip");

            focus.setValue(true);

        }


    }


    public void setFrom(CharSequence s, int start, int before, int count) {

        modelAddTrip.setFrom(String.valueOf(s));


    }

    public void setTo(CharSequence s, int start, int before, int count) {


        modelAddTrip.setTo(String.valueOf(s));


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

        if (id==2){


        }else {
            DatePickerDialog.OnDateSetListener date = openCalenderFrom((TextView) view);
            new DatePickerDialog(view.getContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }


    }


    public void dateUntil(View view) {


        if (id==2){



        }else {

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

        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        view.setText(sdf.format(myCalendar.getTime()));

        modelAddTrip.setDatFrom(sdf.format(myCalendar.getTime()));
    }


    private void updateLabelUntil(TextView view) {


        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        view.setText(sdf.format(myCalendar.getTime()));

        modelAddTrip.setDatUntil(sdf.format(myCalendar.getTime()));
    }

    public void addTrip(View view) {




        if (id==2){


            replace(new HomCompanyFragment(), R.id.container_home_company, ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction(), view.getContext().getString(R.string.tag_hom_company));


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
                repository.addTrip(modelAddTrip, id_company, (Button) view, progress);

            }

        }




    }


    private boolean isEmpty(String s) {

        return TextUtils.isEmpty(s);
    }


    private void setToastError(String msg, View view) {

        StyleableToast.makeText(view.getContext(), msg, Toast.LENGTH_LONG, R.style.error_company).show();


    }

}


