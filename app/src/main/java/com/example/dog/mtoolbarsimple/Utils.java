package com.example.dog.mtoolbarsimple;

import android.annotation.SuppressLint;
import android.os.Build;

import java.lang.annotation.Documented;

public class Utils {

    public static boolean isSdkVersionHigherM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    public static boolean isSdkVersionHigherKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
}
