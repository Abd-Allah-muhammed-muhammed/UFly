package com.abdallah.ufly.adpter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemBookedBinding;
import com.abdallah.ufly.model.passenger_booked.Datum;


import java.util.ArrayList;


public class BookedInfoAdapter extends RecyclerView.Adapter<BookedInfoAdapter.BookedInfoViewHolder> {

    private ArrayList<Datum> bookedInfoArrayList;


    @NonNull
    @Override
    public BookedInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookedBinding itemBookedBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_booked, parent, false);
        return new BookedInfoViewHolder(itemBookedBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull final BookedInfoAdapter.BookedInfoViewHolder holder, int position) {


        final Datum datum = bookedInfoArrayList.get(position);
        holder.itemBookedBinding.setPassBook(datum);

        String is_paid = datum.getBooked().getIs_paid();

        switch (is_paid){


            case "1":
                holder.itemBookedBinding.isPaid.setText(holder.itemBookedBinding.getRoot().getContext().getString(R.string.paid));

                break;


            case "0":
                holder.itemBookedBinding.isPaid.setText(holder.itemBookedBinding.getRoot().getContext().getString(R.string.not_paid));

                break;
        }


        String id_payment = datum.getBooked().getId_payment();

        switch (id_payment){

            case "Cash":

                holder.itemBookedBinding.payment.setCompoundDrawablesWithIntrinsicBounds
                        (null, null, ContextCompat.getDrawable(holder.itemBookedBinding.getRoot().getContext()
                                ,R.drawable.ic_attach_money_black_24dp), null)
                        ;

                break;


            case "Visa":

                holder.itemBookedBinding.payment.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(holder.itemBookedBinding.getRoot().getContext(),R.drawable.ic_payment_black_24dp), null)
;

                break;


        }




        holder.itemBookedBinding.phonePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+datum.getPassenger().getPhone()));
                holder.itemBookedBinding.getRoot().getContext().startActivity(intent);
            }
        });


    }


    public void setBookedInfoList(ArrayList<Datum> bookedInfoList) {
        this.bookedInfoArrayList = bookedInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (bookedInfoArrayList != null) {
            return bookedInfoArrayList.size();
        } else {
            return 0;
        }
    }

    public class BookedInfoViewHolder extends RecyclerView.ViewHolder {
        private ItemBookedBinding itemBookedBinding;

        public BookedInfoViewHolder(@NonNull ItemBookedBinding itemView) {
            super(itemView.getRoot());
            this.itemBookedBinding = itemView;
        }
    }


}
