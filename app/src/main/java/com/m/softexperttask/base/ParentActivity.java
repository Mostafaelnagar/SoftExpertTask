package com.m.softexperttask.base;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.m.softexperttask.R;


public class ParentActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (resultCode == RESULT_OK) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_home_container);

                assert fragment != null;
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        } catch (Exception ex) {
            Log.e("exceptionBase", ex.getMessage());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
