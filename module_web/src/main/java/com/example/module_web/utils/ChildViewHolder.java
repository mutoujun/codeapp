package com.example.module_web.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_web.R;

/**
 * Created by Administrator on 2018/2/23.
 */

public class ChildViewHolder {
    private TextView mTvTitle;
    private ImageView mImage;
    private View itemView;

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public ImageView getImage() {
        return mImage;
    }

    public View getItemView() {
        return itemView;
    }

    public ChildViewHolder(View itemView){

        this.itemView = itemView;
        mTvTitle = itemView.findViewById(R.id.label_expand_child);
        mImage = itemView.findViewById(R.id.img_expand_child);
    }
}
