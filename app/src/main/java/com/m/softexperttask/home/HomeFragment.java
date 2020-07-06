package com.m.softexperttask.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.m.softexperttask.R;
import com.m.softexperttask.base.BaseFragment;
import com.m.softexperttask.databinding.FragmentHomeBinding;
import com.m.softexperttask.home.viewModels.HomeViewModels;

public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding homeBinding;
    private HomeViewModels homeViewModels;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeViewModels = new HomeViewModels();
        homeBinding.setHomeViewModel(homeViewModels);
        liveDataListeners();
        checkConnection();
        homeBinding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeViewModels.getHomeData(homeViewModels.getPage_num());
            }
        });
        return homeBinding.getRoot();
    }


    private void liveDataListeners() {
        homeViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
                if (result == View.GONE) {
                    homeBinding.swipeContainer.setRefreshing(false);
                }
            }
        });
        ConnectionLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isConnected) {
                if (isConnected) {
                    if (noInternetDialog != null)
                        noInternetDialog.destroy();
                    homeViewModels.getHomeData(homeViewModels.getPage_num());
                } else {
                    internetDialog();
                }
            }
        });

    }

//    private void pagatination() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        homeBinding.homeCarsRec.addOnScrollListener(new)
//        storesBinding.recMainHome.setLayoutManager(linearLayoutManager);
//    }

}
