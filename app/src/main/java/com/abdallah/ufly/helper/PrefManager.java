package com.abdallah.ufly.helper;

import android.content.SharedPreferences;
import android.content.Context;
public class PrefManager {

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        Context _context;

        // shared pref mode
        int PRIVATE_MODE = 0;

        // Shared preferences file name
        private static final String PREF_NAME = "ufly_abdalah";
        private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
        private static final String TOKEN = "token";
        private static final String ISLOGED = "is_loged";

        public PrefManager(Context context) {
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        }

        public void setFirstTimeLaunch(boolean isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
            editor.commit();
        }

        public boolean isFirstTimeLaunch() {
            return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
        }


        public void saveToken(String token){
           editor.putString(TOKEN,token) ;
           editor.commit();
        }



        public String getToken(){

            return pref.getString(TOKEN,"");

        }



        public void clear(){
            editor.clear();
            editor.commit();


        }



        public void setIsLoged (boolean isLoged){

            editor.putBoolean(ISLOGED,isLoged);
            editor.commit();


        }


        public boolean isLoged (){

            return pref.getBoolean(ISLOGED,false);
        }

    public void removeToken() {


            editor.remove(TOKEN);
            editor.commit();
    }
}
