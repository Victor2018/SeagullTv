package com.victor.seagull.holder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.seagull.R;
import com.victor.widget.ColorTextView;

/**
 * Created by Administrator on 2017/11/24.
 */

public class YoutubeContentViewHolder extends ContentViewHolder implements View.OnClickListener,View.OnLongClickListener {
    public static final int ON_ITEM_CLICK        = 0;
    public static final int ON_ITEM_LONG_CLICK  = 1;
    public ImageView mIvImg;
    public ImageView mIvIcon;
    public ColorTextView mCtvTitle,mCtvLikeCount,mCtvCommentsCount,mCtvRepostsCount;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public YoutubeContentViewHolder(View view) {
        super(view);
        mIvImg = (ImageView) itemView.findViewById(R.id.iv_img);
        mIvIcon = (ImageView) itemView.findViewById(R.id.iv_meipai_frag_icon);
        mCtvTitle = (ColorTextView) view.findViewById(R.id.ctv_title);
        mCtvLikeCount = (ColorTextView) view.findViewById(R.id.ctv_like_count);
        mCtvCommentsCount = (ColorTextView) view.findViewById(R.id.ctv_comments_count);
        mCtvRepostsCount = (ColorTextView) view.findViewById(R.id.ctv_reposts_count);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener == null) {
            return;
        }
        mOnItemClickListener.onItemClick(null, view, getAdapterPosition(), ON_ITEM_CLICK);
    }

    @Override
    public boolean onLongClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, view, getAdapterPosition(), ON_ITEM_LONG_CLICK);
        }
        return false;
    }

}
