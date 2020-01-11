package com.abdallah.ufly.ui.company.add_trip;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.addTrip.ModelAddTrip;
import com.abdallah.ufly.repository.RepositoryAddTrip;
import com.muddzdev.styleabletoast.StyleableToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTripViewModel extends ViewModel {


  public   MutableLiveData<Integer> progress ;
  ModelAddTrip modelAddTrip ;

  private PrefManager prefManager ;
  RepositoryAddTrip repository;

  final Calendar myCalendar;
    public AddTripViewModel() {

      modelAddTrip = new ModelAddTrip();
      progress = new MutableLiveData<>();
      progress.setValue(8);
      repository = new RepositoryAddTrip();

      myCalendar= Calendar.getInstance();

    }





    public void setFrom(CharSequence s, int start, int before, int count) {

      modelAddTrip.setFrom(String.valueOf(s));


    }

    public void setTo(CharSequence s, int start, int before, int count) {


      modelAddTrip.setTo(String.valueOf(s));


    }public void setIncludes(CharSequence s, int start, int before, int count) {

      modelAddTrip.setIncludse(String.valueOf(s));


    }public void setDescription(CharSequence s, int start, int before, int count) {

      modelAddTrip.setDescription(String.valueOf(s));


    }
    public void setPrice(CharSequence s, int start, int before, int count) {



      modelAddTrip.setPrice(String.valueOf(s));

    } public void setPassengers(CharSequence s, int start, int before, int count) {


      modelAddTrip.setPassengers(String.valueOf(s));

    }

    public void  dateFrom (View view){
      DatePickerDialog.OnDateSetListener date = openCalenderFrom((TextView) view);
      new DatePickerDialog(view.getContext(), date, myCalendar
              .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
              myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }



  public void  dateUntil (View view){


    DatePickerDialog.OnDateSetListener date = openCalenderUntil((TextView) view);


    new DatePickerDialog(view.getContext(), date, myCalendar
            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)).show();


    }

  private  DatePickerDialog.OnDateSetListener openCalenderUntil(final TextView textView) {

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



  private DatePickerDialog.OnDateSetListener  openCalenderFrom(final TextView textView) {



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

  public void  addTrip (View view ){


      if (isEmpty(modelAddTrip.getFrom())){
        setToastError("please Enter From" , view);

      }else if (isEmpty(modelAddTrip.getTo())){

        setToastError("please Enter To" , view);
      }else if (isEmpty(modelAddTrip.getDatFrom())){

        setToastError("please Enter Date From" , view);
      }else if (isEmpty(modelAddTrip.getDatUntil())){

        setToastError("please Enter Date Until" , view);
      }else if (isEmpty(modelAddTrip.getDescription())){

        setToastError("please Enter the Description" , view);
      }else if (isEmpty(modelAddTrip.getPrice())){

        setToastError("please Enter The price" , view);
      }else if (isEmpty(modelAddTrip.getPassengers())){

        setToastError("please Enter the number of passengers" , view);
      }else {
          prefManager = new PrefManager(view.getContext());
          String id_company = prefManager.getID_Company();
          repository.addTrip(modelAddTrip,id_company , (Button) view,progress);

      }



  }



  private boolean isEmpty(String s){

      return TextUtils.isEmpty(s);
  }



  private void setToastError(String msg , View view){

    StyleableToast.makeText(view.getContext(),msg, Toast.LENGTH_LONG, R.style.error_company).show();


  }

    }


