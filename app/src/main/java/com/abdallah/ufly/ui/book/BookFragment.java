package com.abdallah.ufly.ui.book;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.BookFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.book.BookModelResponse;
import com.abdallah.ufly.model.login.LoginResponse;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.my_trip.MyTripFragment;
import com.muddzdev.styleabletoast.StyleableToast;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class BookFragment extends Fragment  implements BookResultCallBacks{

    private BookViewModel mViewModel;

    BookFragmentBinding binding ;
//    public static BookFragment newInstance() {
//        return new BookFragment();
//    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.book_fragment,container,false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this ,new BookViewModelFactory(this)).get(BookViewModel.class);

        getDataIntent();

        binding.setBook(mViewModel);
        binding.bookBook.setText("");


        binding.bookCash.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               mViewModel.shapCash(binding.bookCash ,binding.bookVisa ,binding.bookBook);
                return false;
            }
        });


        binding.bookVisa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mViewModel.shapVisa(binding.bookCash ,binding.bookVisa ,binding.bookBook);
                return false;
            }
        });




       return binding.getRoot();
    }

    private void getDataIntent() {

        Bundle arguments = getArguments();

        String price = arguments.getString(getString(R.string.price));
        binding.bookPrice.setText(price);

        mViewModel.setArgments(arguments);
//
//        prefManager = new PrefManager(getContext());
//         token = prefManager.getToken();
//         trip_id = arguments.getInt("trip_id");
//         id_includes = arguments.getString("id_includes");


    }


    @Override
    public void onError(String msg) {
        StyleableToast.makeText(getContext(), msg, Toast.LENGTH_LONG, R.style.error).show();

    }


    @Override
    public void response(BookModelResponse response) {


try {


    if (response.getStatus()==0){
        StyleableToast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG, R.style.success).show();

        replace(new MyTripFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_mytrip));

    }else if (response.getStatus()==4){

        StyleableToast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG, R.style.error).show();
        replace(new MyTripFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_mytrip));

    }else {

        StyleableToast.makeText(getContext(), "please tray again later :", Toast.LENGTH_LONG, R.style.error).show();

        replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_home));
    }

}catch (Exception e){

    StyleableToast.makeText(getContext(), "please tray again later :", Toast.LENGTH_LONG, R.style.error).show();

    replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_home));


}



    }



}
