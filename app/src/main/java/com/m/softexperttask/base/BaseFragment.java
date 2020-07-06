package com.m.softexperttask.base;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.m.softexperttask.BaseActivity;
 import com.m.softexperttask.R;
import com.m.softexperttask.base.volleyutils.MyApplication;

import org.imaginativeworld.oopsnointernet.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.NoInternetDialog;


public class BaseFragment extends Fragment implements ConnectivityReceiver.ConnectivityReceiverListener {
    Context context;
    public MutableLiveData<Boolean> ConnectionLiveData;
    // No Internet Dialog
    public NoInternetDialog noInternetDialog;

    public void accessLoadingBar(int visiablity) {
        try {
            if (visiablity == View.VISIBLE) {
                ((BaseActivity) context).activityBaseBinding.pbBaseLoadingBar.setVisibility(visiablity);

            } else {
                ((BaseActivity) context).activityBaseBinding.pbBaseLoadingBar.setVisibility(visiablity);
            }

        } catch (ClassCastException e) {
            e.getStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.context = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        ConnectionLiveData = new MutableLiveData<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);

    }

    // Method to manually check connection status
    public void checkConnection() {
        ConnectionLiveData.setValue(ConnectivityReceiver.isConnected());
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        ConnectionLiveData.setValue(isConnected);
    }

    public void internetDialog() {
        Resources resources = getActivity().getResources();
        // No Internet Dialog
        NoInternetDialog.Builder builder1 = new NoInternetDialog.Builder(getActivity());

        builder1.setConnectionCallback(new ConnectionCallback() { // Optional
            @Override
            public void hasActiveConnection(boolean hasActiveConnection) {
                // ...
            }
        });
        builder1.setCancelable(false); // Optional
        builder1.setNoInternetConnectionTitle(resources.getString(R.string.connection_invaild_msg)); // Optional
        builder1.setNoInternetConnectionMessage(resources.getString(R.string.connection_invaild_body)); // Optional
        builder1.setShowInternetOnButtons(true); // Optional
        builder1.setPleaseTurnOnText(resources.getString(R.string.connection_On)); // Optional
        builder1.setWifiOnButtonText(resources.getString(R.string.connection_Wifi)); // Optional
        builder1.setMobileDataOnButtonText(resources.getString(R.string.connection_Data)); // Optional

        builder1.setOnAirplaneModeTitle(resources.getString(R.string.connection_invaild_msg)); // Optional
        builder1.setOnAirplaneModeMessage(resources.getString(R.string.connection_AirPlane)); // Optional
        builder1.setPleaseTurnOffText(resources.getString(R.string.connection_AirPlane_TurnOff)); // Optional
        builder1.setAirplaneModeOffButtonText(resources.getString(R.string.connection_AirPlane_Mode)); // Optional
        builder1.setShowAirplaneModeOffButtons(true); // Optional

        noInternetDialog = builder1.build();

    }

    @Override
    public void onPause() {
        super.onPause();

        // No Internet Dialog
        if (noInternetDialog != null) {
            noInternetDialog.destroy();
        }


    }
}
