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
    private static final String ISLOGED_COMPANY = "is_loged_company";
    private static final String ID_COMPANY = "id_company";
    private static final String QR  = "qr";
    private static final String IS_VALID_MAIL  = "IS_VALID_MAIL";
    private static final String ID_VALIDATION  = "ID_VALIDATION";
    private static final String EMAIL  = "EMAIL";
    private static final String ADDRESS  = "address";

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


    public void saveToken(String token) {
        editor.putString(TOKEN, token);
        editor.commit();
    }
 public void saveQR(String qr) {
        editor.putString(QR, qr);
        editor.commit();
    }


 public void saveIDValidition(String id) {
        editor.putString(ID_VALIDATION, id);
        editor.commit();
    }


    public void savEmail(String mail) {
        editor.putString(EMAIL, mail);
        editor.commit();
    }


    public String getToken() {

        return pref.getString(TOKEN, "");

    }


    public String getIdValidation() {

        return pref.getString(ID_VALIDATION, "");

    } public String getEmail() {

        return pref.getString(EMAIL, "");

    }


 public boolean isvalidMAil() {

        return pref.getBoolean(IS_VALID_MAIL, false);


    }


    public void setIsValidMail(boolean isValidMail) {
        editor.putBoolean(IS_VALID_MAIL, isValidMail);
        editor.commit();
    }

    public String getQr() {

        return pref.getString(QR, "");

    }


    public void clear() {
        editor.clear();
        editor.commit();


    }


    public void setIsLoged(boolean isLoged) {

        editor.putBoolean(ISLOGED, isLoged);
        editor.commit();


    }

    public void setIslogedCompany(boolean islogedCompany) {

        editor.putBoolean(ISLOGED_COMPANY, islogedCompany);
        editor.commit();


    }


    public boolean isLoged() {

        return pref.getBoolean(ISLOGED, false);


    } public boolean isLogedCompany() {

        return pref.getBoolean(ISLOGED_COMPANY, false);
    }

    public void removeToken() {


        editor.remove(TOKEN);
        editor.commit();
    }

    public String getID_Company() {

        return pref.getString(ID_COMPANY, "");

    }


    public void saveIdCompany(String idCompany) {
        editor.putString(ID_COMPANY, idCompany);
        editor.commit();
    }

    public void removeIdCompany() {

        editor.remove(ID_COMPANY);
        editor.commit();
    }

    public void saveAddress(String address) {


        editor.putString(ADDRESS,address);
        editor.commit();

    }



    public String getAddress(){


        return pref.getString(ADDRESS,"Cairo");
    }
}
