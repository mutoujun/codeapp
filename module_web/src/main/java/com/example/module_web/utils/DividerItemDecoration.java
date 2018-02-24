package com.example.module_web.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Administrator on 2018/2/19.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {android.R.attr.listDivider};

    public static int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private final Drawable mDivider;
    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) throws IllegalAccessException {
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) throws IllegalAccessException {
        if(orientation!=HORIZONTAL_LIST&&orientation!=VERTICAL_LIST){
            throw new IllegalAccessException("无效的方向");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if(mOrientation==HORIZONTAL_LIST){
            drawHorizontal(c,parent);
        }else if(mOrientation==VERTICAL_LIST){
            drawVertical(c,parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for(int i = 0;i<childCount;i++){
            View childAt = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int top = childAt.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for(int i = 0;i<childCount;i++){
            View childAt = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation==VERTICAL_LIST){
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }else {
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }
    }
}
