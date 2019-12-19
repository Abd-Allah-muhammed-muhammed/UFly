package com.abdallah.ufly.model.setting;

import android.annotation.SuppressLint;
import android.widget.ImageButton;

import androidx.annotation.IdRes;
import androidx.databinding.BindingAdapter;

public class SettingModel {



    public SettingModel(String textSetting, int resIconOne, int resIconTwo ,int text_color) {
        this.textSetting = textSetting;
        this.resIconOne = resIconOne;
        this.text_color = text_color;
        this.resIconTwo = resIconTwo;
    }

    public int getText_color() {
        return text_color;
    }

    public void setText_color(int text_color) {
        this.text_color = text_color;
    }

    public int text_color ;
    public String textSetting ;

     public int resIconOne ;
     public int resIconTwo ;

    public String getTextSetting() {
        return textSetting;
    }

    public void setTextSetting(String textSetting) {
        this.textSetting = textSetting;
    }

    public int getResIconOne() {
        return resIconOne;
    }

    public void setResIconOne(int resIconOne) {
        this.resIconOne = resIconOne;
    }

    public int getResIconTwo() {
        return resIconTwo;
    }

    public void setResIconTwo(int resIconTwo) {
        this.resIconTwo = resIconTwo;
    }




    @SuppressLint("ResourceType")
    @BindingAdapter("load_image")
    public static void loadImage(ImageButton view, @IdRes int imageId) {

        view.setImageResource(imageId);
        //Logic
    }
}
