package com.shanian.arch.mvvm.actorlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shanian.arch.mvvm.R;
import com.shanian.arch.mvvm.bean.EntriesBean;
import com.shanian.arch.mvvm.databinding.MovieComingItemBinding;

import me.drakeet.multitype.ItemViewBinder;

public class MovieComingItemBinder extends ItemViewBinder<EntriesBean, MovieComingItemBinder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        MovieComingItemBinding movieComingItemBinding = DataBindingUtil.inflate(inflater, R.layout.movie_coming_item, parent, false);
        return new ViewHolder(movieComingItemBinding);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull EntriesBean item) {
        holder.movieComingItemBinding.setItem(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private MovieComingItemBinding movieComingItemBinding;

        public ViewHolder(@NonNull MovieComingItemBinding itemViewBinding) {
            super(itemViewBinding.getRoot());
            movieComingItemBinding = itemViewBinding;
        }
    }


}
