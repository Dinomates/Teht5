package com.example.teht5;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.teht5.R;

public class Counter {
    private int currentValue, maxValue, minValue;
    private String forUpdate;
    private boolean hex, dec;

    public Counter(int max, int min) {
        this.currentValue = 0;
        this.maxValue = max;
        this.minValue = min;
        this.forUpdate = "0";
        this.hex = false;
        this.dec = false;
    }

    public int getCurrentValue() {
  /*      if (!hex) {
            //if hex is false, simply turn the current value to a String
            this.forUpdate = (Integer.toString(this.currentValue));
            return this.forUpdate;
        }
        else {
            //if hex is true, convert the current value to Hex
            convertToHex(currentValue);
            return this.forUpdate;
        } */
        return this.currentValue;
    }

    public void setToZero() {
        this.currentValue = 0;
    }

    public int clickedButtonPlus() {
        if (currentValue < maxValue) {
            currentValue++;
        }
        return currentValue;
    }

    public int clickedButtonMinus() {
        if (currentValue > minValue) {
            currentValue--;
        }
        return currentValue;

    }

    public String convertToHex(int currentValue) {
        this.forUpdate = Integer.toHexString(currentValue);
        this.forUpdate = this.forUpdate.toUpperCase();
        return this.forUpdate;
    }

    public void changeToDec(){
        this.dec = true;
        this.hex = false;
    }

    public void changeToHex(){
        this.dec = false;
        this.hex = true;
    }

    public void setValue (int newValue){
        this.currentValue = newValue;
    }


}