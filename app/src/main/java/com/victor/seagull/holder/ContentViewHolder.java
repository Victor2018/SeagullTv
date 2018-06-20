package com.victor.seagull.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.victor.seagull.R;

/**
 * Created by Administrator on 2017/11/24.
 */

public class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    public AdapterView.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public ContentViewHolder(View view) {
        super(view);
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, view, getAdapterPosition(), 0);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, v, getAdapterPosition(), -1);
        }
        return false;
    }
}
