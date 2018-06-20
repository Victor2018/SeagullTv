package com.victor.adapter;import android.content.Context;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.LinearSnapHelper;import android.support.v7.widget.RecyclerView;import android.view.Gravity;import android.view.LayoutInflater;import android.view.MotionEvent;import android.view.View;import android.view.ViewGroup;import android.widget.AdapterView;import android.widget.TextView;import com.victor.data.LiveCategory;import com.victor.seagull.R;import com.victor.util.GravitySnapHelper;import java.util.List;public class SnapAdapter extends RecyclerView.Adapter<SnapAdapter.ViewHolder> {    public static final int VERTICAL = 0;    public static final int HORIZONTAL = 1;    private Context mContext;    private static AdapterView.OnItemClickListener mOnItemClickListener;    private static List<LiveCategory> datas;    public void setDatas(List<LiveCategory> datas) {        this.datas = datas;    }    public List<LiveCategory> getDatas() {        return datas;    }    // Disable touch detection for parent recyclerView if we use vertical nested recyclerViews    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {        @Override        public boolean onTouch(View v, MotionEvent event) {            v.getParent().requestDisallowInterceptTouchEvent(true);            return false;        }    };    public SnapAdapter(Context context,AdapterView.OnItemClickListener listener) {        mContext = context;        mOnItemClickListener = listener;    }    @Override    public int getItemViewType(int position) {        LiveCategory snap = datas.get(position);        switch (snap.getGravity()) {            case Gravity.CENTER_VERTICAL:                return VERTICAL;            case Gravity.CENTER_HORIZONTAL:                return HORIZONTAL;            case Gravity.START:                return HORIZONTAL;            case Gravity.TOP:                return VERTICAL;            case Gravity.END:                return HORIZONTAL;            case Gravity.BOTTOM:                return VERTICAL;        }        return HORIZONTAL;    }    @Override    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {        View view = viewType == VERTICAL ? LayoutInflater.from(parent.getContext())                .inflate(R.layout.adapter_snap_vertical, parent, false)                : LayoutInflater.from(parent.getContext())                .inflate(R.layout.adapter_snap, parent, false);        if (viewType == VERTICAL) {            view.findViewById(R.id.recyclerView).setOnTouchListener(mTouchListener);        }        return new ViewHolder(view);    }    @Override    public void onBindViewHolder(ViewHolder holder, int position) {        LiveCategory snap = datas.get(position);        holder.snapTextView.setText(snap.getChannel_category());        if (snap.getGravity() == Gravity.START || snap.getGravity() == Gravity.END) {            holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder                    .recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));            holder.recyclerView.setOnFlingListener(null);            new GravitySnapHelper(snap.getGravity()).attachToRecyclerView(holder.recyclerView);        } else if (snap.getGravity() == Gravity.CENTER_HORIZONTAL ||                snap.getGravity() == Gravity.CENTER_VERTICAL) {            holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder                    .recyclerView.getContext(), snap.getGravity() == Gravity.CENTER_HORIZONTAL ?                    LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL, false));            holder.recyclerView.setOnFlingListener(null);            new LinearSnapHelper().attachToRecyclerView(holder.recyclerView);        } else if (snap.getGravity() == Gravity.CENTER) { // Pager snap            holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder                    .recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));            holder.recyclerView.setOnFlingListener(null);            new GravitySnapHelper(snap.getGravity()).attachToRecyclerView(holder.recyclerView);        } else { // Top / Bottom            holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder                    .recyclerView.getContext()));            holder.recyclerView.setOnFlingListener(null);            new GravitySnapHelper(snap.getGravity()).attachToRecyclerView(holder.recyclerView);        }        holder.recyclerView.setAdapter(new Adapter(mContext,snap.getGravity() == Gravity.START                || snap.getGravity() == Gravity.END                || snap.getGravity() == Gravity.CENTER_HORIZONTAL                || snap.getGravity() == Gravity.CENTER, snap.getChannels(),mOnItemClickListener));    }    @Override    public int getItemCount() {        return datas == null ? 0 : datas.size();    }    public static class ViewHolder extends RecyclerView.ViewHolder {        public TextView snapTextView;        public RecyclerView recyclerView;        public ViewHolder(View itemView) {            super(itemView);            snapTextView = (TextView) itemView.findViewById(R.id.ctv_snap_title);            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);        }    }}