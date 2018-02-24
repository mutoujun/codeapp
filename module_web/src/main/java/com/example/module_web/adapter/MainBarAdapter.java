package com.example.module_web.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_web.R;
import com.example.module_web.beans.TitleBar;

import java.util.List;

/**
 * Created by Administrator on 2018/2/17.
 */

public class MainBarAdapter extends RecyclerView.Adapter<MainBarAdapter.ViewHolder> {
    private static final String TAG = "bug";
    private Context mContext;
    private List<TitleBar> mBarList;
    private OnItemClickListener mOnItemClickListener;

    public MainBarAdapter(Context context,List<TitleBar> barList){
        this.mContext = context;
        this.mBarList = barList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.web_item_appbar,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final TitleBar titleBar = mBarList.get(position);
        if(position==0){
            holder.mBtnClose.setVisibility(View.GONE);
        }
//        holder.mImgIcon.setImageResource(titleBar.getImgIcon());
        final String title = titleBar.getTvTitle();
        holder.mTvTitle.setText(title);
        holder.mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,position,title);
            }
        });
        holder.mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastTitle = mBarList.get(position - 1).getTvTitle();
                mOnItemClickListener.onItemClick(v,position,title,lastTitle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBarList.size();
    }



    public void addItem(TitleBar titleBar){
        if(titleBar!=null){
            mBarList.add(titleBar);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position){
        mBarList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImgIcon;
        TextView mTvTitle;
        ImageButton mBtnClose;
        View mItemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.mItemView = itemView;
            mImgIcon = itemView.findViewById(R.id.bar_img_icon);
            mTvTitle = itemView.findViewById(R.id.bar_tv_title);
            mBtnClose = itemView.findViewById(R.id.bar_btn_close);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v ,int position,String title);
        void onItemClick(View v,int position,String title,String lastTitle);
    }
}
