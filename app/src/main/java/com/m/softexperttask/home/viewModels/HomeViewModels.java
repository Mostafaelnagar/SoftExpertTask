package com.m.softexperttask.home.viewModels;


import android.view.View;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.m.softexperttask.base.BaseViewModel;
import com.m.softexperttask.base.constantsutils.WebServices;
import com.m.softexperttask.base.volleyutils.ConnectionHelper;
import com.m.softexperttask.base.volleyutils.ConnectionListener;
import com.m.softexperttask.base.volleyutils.URLS;
import com.m.softexperttask.home.adapters.HomeAdapter;
import com.m.softexperttask.home.models.HomeResponse;


public class HomeViewModels extends BaseViewModel {
    private HomeAdapter homeAdapter;
    private int page_num = 1;

    public HomeViewModels() {
    }

    public int getPage_num() {
        return page_num;
    }

    public void getHomeData(int page_num) {

        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                HomeResponse checkResponse = (HomeResponse) response;
                if (checkResponse.getStatus() == WebServices.SUCCESS) {
                    getHomeAdapter().updateData(checkResponse.getData());
                } else if (checkResponse.getStatus() == WebServices.BAD_ERROR) {
                    accessLoadingBar(View.GONE);
                }
                notifyChange();
            }

            @Override
            public void onRequestError(String error) {
                super.onRequestError(error);
                accessLoadingBar(View.GONE);
            }
        }).requestJsonObject(Request.Method.GET, URLS.HOME + page_num, new Object(), HomeResponse.class);
    }

    @BindingAdapter({"app:homeAdapter"})
    public static void getHomeBinding(RecyclerView recyclerView, HomeAdapter homeAdapter) {
        recyclerView.setAdapter(homeAdapter);

    }

    @Bindable
    public HomeAdapter getHomeAdapter() {
        return this.homeAdapter == null ? this.homeAdapter = new HomeAdapter() : this.homeAdapter;
    }
}
