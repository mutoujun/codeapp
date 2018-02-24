package com.example.module_web.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.module_web.R;

/**
 * Created by Administrator on 2018/2/21.
 */

public class Frag00Adapter extends RecyclerView.Adapter<Frag00Adapter.ViewHolder>{
    private Context mContext;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.web_item_frag00_order,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView mTvNum;
        TextView mTvWawa;
        TextView mTvGoods;
        TextView mTvWasMoney;
        TextView mTvIsMoney;
        TextView mTvAddrs;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            mTvNum = itemView.findViewById(R.id.item_frag00_num);
            mTvWawa = itemView.findViewById(R.id.item_frag00_wawa);
            mTvGoods = itemView.findViewById(R.id.item_frag00_goods);
            mTvWasMoney = itemView.findViewById(R.id.item_frag00_wasmoney);
            mTvIsMoney = itemView.findViewById(R.id.item_frag00_ismoney);
            mTvAddrs = itemView.findViewById(R.id.item_frag00_addrs);
        }
    }
}
