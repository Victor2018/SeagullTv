package com.victor.adapter;import android.content.Context;import android.os.Bundle;import android.support.v7.widget.RecyclerView;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.AdapterView;import android.widget.ImageView;import android.widget.TextView;import com.bumptech.glide.Glide;import com.victor.data.GankInfo;import com.victor.module.DataObservable;import com.victor.seagull.R;import com.victor.util.Constant;import com.victor.widget.ColorTextView;import java.util.List;/** * Created by victor on 2016/6/1. */public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{    private String TAG = "GankAdapter";    private final LayoutInflater mLayoutInflater;    private final Context mContext;    private static List<GankInfo> datas;    protected int mHeaderCount = 1;//头部View个数    protected int mBottomCount = 1;//底部View个数    private int ITEM_TYPE_HEADER = 0;    private int ITEM_TYPE_CONTENT = 1;    private int ITEM_TYPE_BOTTOM = 2;    private boolean isHeaderVisible = true;    private boolean isFooterVisible = true;    private static AdapterView.OnItemClickListener mOnItemClickListener;    public List<GankInfo> getDatas() {        return datas;    }    public void setDatas(List<GankInfo> datas) {        this.datas = datas;    }    public GankAdapter(Context context, AdapterView.OnItemClickListener listener) {        mContext = context;        mOnItemClickListener = listener;        mLayoutInflater = LayoutInflater.from(context);    }    public void setHeaderVisible (boolean visible) {        isHeaderVisible = visible;        mHeaderCount = 1;        if (!isHeaderVisible) {            mHeaderCount = 0;        }        notifyDataSetChanged();    }    public void setFooterVisible (boolean visible) {        isFooterVisible = visible;        mBottomCount = 1;        if (!isFooterVisible) {            mBottomCount = 0;        }        notifyDataSetChanged();    }    @Override    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {        if (viewType == ITEM_TYPE_HEADER) {            return onCreateHeaderView(parent);        } else if (viewType == ITEM_TYPE_CONTENT) {            return onCreateContentView(parent);        } else if (viewType == ITEM_TYPE_BOTTOM) {            return onCreateBottomView(parent);        }        return null;    }    public RecyclerView.ViewHolder onCreateHeaderView (ViewGroup parent){        return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_header, parent, false));    }    public RecyclerView.ViewHolder onCreateContentView (ViewGroup parent){        return new ContentViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_gank_content, parent, false));    }    public RecyclerView.ViewHolder onCreateBottomView (ViewGroup parent) {        return new BottomViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_foot, parent, false));    }    @Override    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {        if (holder instanceof HeaderViewHolder) {            ((HeaderViewHolder) holder).mTvTitle.setVisibility(View.GONE);        } else if (holder instanceof ContentViewHolder) {            final ImageView mIvImg = ((ContentViewHolder) holder).mIvImg;            TextView mTvTitle = ((ContentViewHolder) holder).mCtvTitle;            int limit = 48;            if (position < datas.size()) {                GankInfo data = datas.get(position);                String desc = data.getDesc().length() > limit ? data.getDesc().substring(0, limit) + "..." : data.getDesc();                mTvTitle.setText(desc);                String imgUrl = datas.get(position).getUrl();                Glide.with(mContext).load(imgUrl).centerCrop().placeholder(R.mipmap.default_img).error(R.mipmap.default_img).into(mIvImg);//                ImageUtils.get().loadImage(mIvImg,imgUrl,R.mipmap.default_img);            }        } else if (holder instanceof BottomViewHolder) {//            ((BottomViewHolder) holder).mTvTitle.setVisibility(View.GONE);        }    }    @Override    public int getItemViewType(int position) {        int ITEM_TYPE = ITEM_TYPE_CONTENT;        int dataItemCount = getContentItemCount();        if (mHeaderCount != 0 && position < mHeaderCount) {//头部View            ITEM_TYPE = ITEM_TYPE_HEADER;        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {//底部View            ITEM_TYPE = ITEM_TYPE_BOTTOM;        }        return ITEM_TYPE;    }    public boolean isHeaderView(int position) {        return mHeaderCount != 0 && position < mHeaderCount;    }    public boolean isBottomView(int position) {        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());    }    public int getContentItemCount() {        return datas == null ? 0 : datas.size();    }    @Override    public int getItemCount() {        return mHeaderCount + getContentItemCount() + mBottomCount;    }    public static class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{        ImageView mIvImg;        ColorTextView mCtvTitle;        ContentViewHolder(View view) {            super(view);            mIvImg = (ImageView) itemView.findViewById(R.id.iv_img);            mCtvTitle = (ColorTextView) view.findViewById(R.id.ctv_title);            view.setOnClickListener(this);            view.setOnLongClickListener(this);        }        @Override        public void onClick(View view) {            if (mOnItemClickListener != null) {                if (getAdapterPosition() < datas.size()) {                    mOnItemClickListener.onItemClick(null,view,getAdapterPosition(),0);                }            }        }        @Override        public boolean onLongClick(View v) {            if (getAdapterPosition() < datas.size()) {                String imgUrl = datas.get(getAdapterPosition()).getUrl();                Bundle bundle = new Bundle();                bundle.putInt(Constant.ACTION_KEY,Constant.Action.SHARE_PICTURE);                bundle.putString(Constant.INTENT_DATA_KEY,imgUrl);                DataObservable.getInstance().setData(bundle);            }            return false;        }    }    public static class HeaderViewHolder extends RecyclerView.ViewHolder {        ImageView mIvImg;        TextView mTvTitle;        public HeaderViewHolder(View itemView) {            super(itemView);            mIvImg = (ImageView) itemView.findViewById(R.id.iv_img);            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);        }    }    public static class BottomViewHolder extends RecyclerView.ViewHolder {        ImageView mIvImg;        TextView mTvTitle;        public BottomViewHolder(View itemView) {            super(itemView);            mIvImg = (ImageView) itemView.findViewById(R.id.iv_img);            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);        }    }}