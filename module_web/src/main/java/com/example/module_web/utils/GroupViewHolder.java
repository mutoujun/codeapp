package com.example.module_web.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_web.R;

/**
 * Created by Administrator on 2018/2/23.
 */

public class GroupViewHolder {
    TextView mTvTitle;
    ImageView mImage;

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public ImageView getImage() {
        return mImage;
    }

    public View getItemView() {
        return itemView;
    }

    View itemView;
    public GroupViewHolder(View itemView){
        this.itemView = itemView;
        mTvTitle = itemView.findViewById(R.id.label_expand_group);
        mImage = itemView.findViewById(R.id.img_expand_group);
    }
}
