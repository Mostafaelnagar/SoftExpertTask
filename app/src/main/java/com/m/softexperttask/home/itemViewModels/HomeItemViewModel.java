package com.m.softexperttask.home.itemViewModels;


import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.m.softexperttask.R;
import com.m.softexperttask.base.BaseViewModel;
import com.m.softexperttask.home.models.HomeData;
import com.squareup.picasso.Picasso;

public class HomeItemViewModel extends BaseViewModel {
    private HomeData homeData;

    public HomeItemViewModel(HomeData homeData) {
        this.homeData = homeData;
    }

    @Bindable
    public HomeData getHomeData() {
        return homeData;
    }

    @BindingAdapter({"carImage"})
    public static void loadImage(ImageView view, String carImage) {
        if (!TextUtils.isEmpty(carImage)) {
            Picasso.get().load(carImage).placeholder(R.color.overlayBackground).into(view);
        }
    }


}
