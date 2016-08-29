package com.khmer.share.api.adapter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.khmer.share.api.adapter.core.ItemBinder;

import java.util.Collection;

/**
 * @Copy Right 2012-2016, Afinos, Inc., or its affiliates
 * @Author: Afinos Team
 **/
public abstract class BaseRecyclerAdapter<K, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> implements View.OnClickListener {
    protected final static int ITEM_DATA = -111;
    protected ObservableList<K> mItems;
    protected ItemBinder mItemBinder;

    public BaseRecyclerAdapter(ItemBinder<K> itemBinder, Collection<K> items) {
        mItemBinder = itemBinder;
        setItems(items);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void setItems(Collection<K> items) {
        if (items == null)
            return;
        if (items instanceof ObservableList) {
            mItems = new ObservableArrayList<>();
            mItems.addAll(items);
        } else {
            mItems.clear();
            mItems.addAll(items);
        }
        notifyItemRangeChanged(0, mItems.size());
    }

    public void replaceItem(int position, K item) {
        if (item == null || position < 0)
            return;
        mItems.add(position, item);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mItems.size());
    }

    public void removeItem(int position) {
        if (position < 0)
            return;
        mItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItems.size());
    }
}
