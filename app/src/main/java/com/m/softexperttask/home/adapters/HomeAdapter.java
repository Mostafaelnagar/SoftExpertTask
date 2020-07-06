package com.m.softexperttask.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.m.softexperttask.R;
import com.m.softexperttask.databinding.HomeItemBinding;
import com.m.softexperttask.home.itemViewModels.HomeItemViewModel;
import com.m.softexperttask.home.models.HomeData;

import java.util.ArrayList;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public List<HomeData> homeDataList;
    Context context;

    public HomeAdapter() {
        homeDataList = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeData dataModel = homeDataList.get(position);
        HomeItemViewModel homeItemViewModels = new HomeItemViewModel(dataModel);

        holder.setViewModel(homeItemViewModels);
    }

    @Override
    public int getItemCount() {
        return this.homeDataList.size();
    }

    //
    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<HomeData> data) {
        this.homeDataList.clear();

        this.homeDataList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HomeItemBinding itemBinding;

        //
        ViewHolder(View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (itemBinding == null) {
                itemBinding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (itemBinding != null) {
                itemBinding.unbind();
            }
        }

        void setViewModel(HomeItemViewModel itemViewModels) {
            if (itemBinding != null) {
                itemBinding.setHomeItemViewModels(itemViewModels);
            }
        }
    }
}
