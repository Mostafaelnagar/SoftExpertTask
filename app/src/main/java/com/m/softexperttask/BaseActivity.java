package com.m.softexperttask;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.m.softexperttask.base.MovementManager;
import com.m.softexperttask.base.ParentActivity;
import com.m.softexperttask.databinding.ActivityBaseBinding;
import com.m.softexperttask.home.HomeFragment;


public class BaseActivity extends ParentActivity {
    public ActivityBaseBinding activityBaseBinding;
    public String lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        MovementManager.addFragment(this, new HomeFragment(), "");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}