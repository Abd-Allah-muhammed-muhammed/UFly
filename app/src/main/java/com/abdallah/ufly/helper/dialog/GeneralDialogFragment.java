package com.abdallah.ufly.helper.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdallah.ufly.R;

public class GeneralDialogFragment extends BaseDialogFragment<GeneralDialogFragment.OnDialogFragmentClickListener> {


    public interface OnDialogFragmentClickListener {
        public void onOkClicked(GeneralDialogFragment dialog);
    }




    // Create an instance of the Dialog with the input
    public static GeneralDialogFragment newInstance(String title, String message , int icon) {
        GeneralDialogFragment frag = new GeneralDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        args.putInt("icon", icon);
        frag.setArguments(args);
        return frag;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_dialog, container, false);

        TextView malertTitle = view.findViewById(R.id.title_dialog);
        TextView aleartMessage = view.findViewById(R.id.msg_dialog);

        ImageView cancel_dialog = view.findViewById(R.id.cancel_dialog);
        ImageView icon_dialog = view.findViewById(R.id.icon_dialog);
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        int icon = getArguments().getInt("icon");
        malertTitle.setText(title);
        aleartMessage.setText(message);

        icon_dialog.setImageResource(icon);

        cancel_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivityInstance().onOkClicked(GeneralDialogFragment.this);
            }
        });



        return view;
    }
}
