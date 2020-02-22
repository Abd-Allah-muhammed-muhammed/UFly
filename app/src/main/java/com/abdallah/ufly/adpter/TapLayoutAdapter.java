package com.abdallah.ufly.adpter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.abdallah.ufly.R;
import com.abdallah.ufly.ui.my_trip.my_trips_fragment.complet.MyCompletTripsFragment;
import com.abdallah.ufly.ui.my_trip.my_trips_fragment.old.MyOldTripsFragment;
import com.abdallah.ufly.ui.my_trip.my_trips_fragment.panding.MyPandingTripsFragment;

public class TapLayoutAdapter  extends FragmentPagerAdapter {

    int [] title = {R.string.my_complet_trips ,R.string.my_panding_trips ,R.string.my_old_trips};
    public TapLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos){
            case 1:
                return new MyPandingTripsFragment();
                case 2 :
                    return  new MyOldTripsFragment();
            case 0:
            default:
                return new  MyCompletTripsFragment();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Complete"  ;
            case 1:
                return "Panding";
            case 2 :
                return "Old";
        }

        return super.getPageTitle(position);
    }
}