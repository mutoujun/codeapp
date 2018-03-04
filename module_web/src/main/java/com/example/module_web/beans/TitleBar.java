package com.example.module_web.beans;

import android.widget.ImageButton;

/**
 * Created by Administrator on 2018/2/18.
 */

public class TitleBar {
    private int mImgIcon;
    private String mTvTitle;
    private ImageButton mImgBtn;

    public TitleBar(String tvTitle){
        this.mTvTitle = tvTitle;
    }
    
    public TitleBar(String tvTitle,ImageButton imgBtn){
        this.mTvTitle = tvTitle;
        this.mImgBtn = imgBtn;
    }
    public TitleBar(int imgIcon,String tvTitle){
        this.mImgIcon = imgIcon;
        this.mTvTitle = tvTitle;
    }

    public int getImgIcon() {
        return mImgIcon;
    }

    public void setImgIcon(int imgIcon) {
        mImgIcon = imgIcon;
    }

    public String getTvTitle() {
        return mTvTitle;
    }

    public void setTvTitle(String tvTitle) {
        mTvTitle = tvTitle;
    }

    public ImageButton getImgBtn() {
        return mImgBtn;
    }

    public void setImgBtn(ImageButton imgBtn) {
        mImgBtn = imgBtn;
    }
}
