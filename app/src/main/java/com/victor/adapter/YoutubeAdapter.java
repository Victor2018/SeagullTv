package com.victor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.victor.data.YoutubeInfo;
import com.victor.seagull.R;
import com.victor.seagull.holder.YoutubeContentViewHolder;

/**
 * @Author Victor
 * @Date Create on 2018/1/17 18:01.
 * @Describe
 */

public class YoutubeAdapter extends BaseRecycleAdapter<YoutubeInfo,RecyclerView.ViewHolder> {
    public YoutubeAdapter(Context context, AdapterView.OnItemClickListener listener) {
        super(context,listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeadVHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindHeadVHolder(RecyclerView.ViewHolder viewHolder, YoutubeInfo data, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateContentVHolder(ViewGroup parent, int viewType) {
        return new YoutubeContentViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_meipai_content, parent, false));
    }

    @Override
    public void onBindContentVHolder(RecyclerView.ViewHolder viewHolder, YoutubeInfo data, int position) {
        YoutubeContentViewHolder contentViewHolder = (YoutubeContentViewHolder) viewHolder;
        String name = data.videoName;
        if (!TextUtils.isEmpty(name)) {
            if (name.length() > 20) {
                name = name.substring(0,20) + "...";
            }
        }
        contentViewHolder.mCtvTitle.setText(data.videoName);
        String imgUrl = "http://ww1.sinaimg.cn/large/0065oQSqly1frsllc19gfj30k80tfah5.jpg";
        Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(0.2f)
                .into(contentViewHolder.mIvImg);
//        ImageUtils.get().loadImage(contentViewHolder.mIvImg,imgUrl,R.mipmap.default_img);
        contentViewHolder.setOnItemClickListener(mOnItemClickListener);
    }

}
