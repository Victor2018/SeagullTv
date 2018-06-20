package com.victor.adapter;import android.content.Context;import android.support.v7.widget.RecyclerView;import android.text.TextUtils;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.AdapterView;import android.widget.ImageView;import android.widget.TextView;import com.bumptech.glide.Glide;import com.victor.data.Channel;import com.victor.seagull.R;import java.util.List;public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {    private static String TAG = "Adapter";    private Context mContext;    private static List<Channel> datas;    private boolean mHorizontal;    private static AdapterView.OnItemClickListener mOnItemClickListener;    public List<Channel> getDatas() {        return datas;    }    public Adapter(Context context,boolean horizontal, List<Channel> datas, AdapterView.OnItemClickListener listener) {        mContext = context;        mHorizontal = horizontal;        this.datas = datas;        mOnItemClickListener = listener;    }    @Override    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {        return mHorizontal ? new ViewHolder(LayoutInflater.from(parent.getContext())                .inflate(R.layout.adapter, parent, false)) :                new ViewHolder(LayoutInflater.from(parent.getContext())                        .inflate(R.layout.adapter_vertical, parent, false));    }    @Override    public void onBindViewHolder(ViewHolder holder, int position) {        Channel data = datas.get(position);        holder.imageView.setImageResource(R.mipmap.logo);        holder.nameTextView.setText(data.getChannel_name());        if (!TextUtils.isEmpty(data.getIcon())) {            Glide.with(mContext).load(data.getIcon()).fitCenter().error(R.mipmap.logo).into(holder.imageView);        }    }    @Override    public int getItemViewType(int position) {        return super.getItemViewType(position);    }    @Override    public int getItemCount() {        return datas == null ? 0 : datas.size();    }    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {        public ImageView imageView;        public TextView nameTextView;        public ViewHolder(View itemView) {            super(itemView);            imageView = (ImageView) itemView.findViewById(R.id.imageView);            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);            itemView.setOnClickListener(this);        }        @Override        public void onClick(View view) {            if (getAdapterPosition() < datas.size()) {                if (mOnItemClickListener != null) {                    mOnItemClickListener.onItemClick(null,view,getAdapterPosition(),datas.get(getAdapterPosition()).getCategory());                }            }        }    }}