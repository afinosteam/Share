package com.khmer.share.api.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khmer.share.api.adapter.core.ItemBinder;
import com.khmer.share.api.handler.ClickHandler;

import java.util.Collection;

/**
 * @Copy Right 2012-2016, Afinos, Inc., or its affiliates
 * @Author: Afinos Team
 **/
public class BindingRecyclerAdapter<T> extends BaseRecyclerAdapter<T, BindingRecyclerAdapter.BindingViewHolder> {
    private ClickHandler<T> mClickHandler;
    private LayoutInflater mInflater;

    public BindingRecyclerAdapter(ItemBinder<T> itemBinder, Collection<T> items) {
        super(itemBinder, items);
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null)
            mInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, viewType, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        holder.binding.setVariable(mItemBinder.getBindingVariable(mItems.get(position)), mItems.get(position));
        holder.binding.getRoot().setTag(ITEM_DATA, mItems.get(position));
        holder.binding.getRoot().setOnClickListener(this);
    }

    public void setClickHandler(ClickHandler<T> clickHandler) {
        mClickHandler = clickHandler;
    }

    @Override
    public void onClick(View view) {
        if (mClickHandler != null) {
            T item = (T) view.getTag(ITEM_DATA);
            mClickHandler.onClicked(item);
        }
    }

    /*Static Recycler View Holder*/
    static class BindingViewHolder extends RecyclerView.ViewHolder {
        public ViewDataBinding binding;

        public BindingViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            binding = viewDataBinding;
        }
    }
}
