package com.m.softexperttask.base;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.m.softexperttask.R;


public class MovementManager {


    //---------Fragments----------//
    private static final int CONTAINER_ID = R.id.fl_home_container;


    public static void addFragment(Context context, Fragment fragment, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(CONTAINER_ID, fragment);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }

}
