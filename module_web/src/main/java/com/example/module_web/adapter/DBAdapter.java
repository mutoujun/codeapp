package com.example.module_web.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorTreeAdapter;

import com.example.module_web.R;
import com.example.module_web.db.DBOpenHelper;
import com.example.module_web.db.DBWrapper;
import com.example.module_web.public_data.Constant;
import com.example.module_web.utils.ChildViewHolder;
import com.example.module_web.utils.GroupViewHolder;

/**
 * Created by Administrator on 2018/2/22.
 */

public class DBAdapter extends CursorTreeAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private GroupViewHolder mGroupHolder;
    private ChildViewHolder mChildHolder;
    private View mGroupItemView;
    private View mChildItemView;
    private DBWrapper mDBWrapper;
    /**
     *
     * @param groupCursor 组的游标
     * @param context
     */
    public DBAdapter(Cursor groupCursor, Context context,DBWrapper dbWrapper) {
        super(groupCursor, context);
        this.mContext = context;
        this.mDBWrapper = dbWrapper;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    protected Cursor getChildrenCursor(Cursor groupCursor) {
        return mDBWrapper.selectFromChild(groupCursor.getString(groupCursor.getColumnIndex(Constant.COLUMN_GROUP_NAME_TYPE)));
    }

    @Override
    protected View newGroupView(Context context, Cursor groupCursor, boolean isExpanded, ViewGroup parent) {
        if(mGroupItemView==null){
            mGroupItemView = mInflater.inflate(R.layout.web_item_expand_group, parent, false);
            mGroupHolder = new GroupViewHolder(mGroupItemView);
        }
        return mGroupItemView;
    }



    @Override
    protected void bindGroupView(View view, Context context, Cursor groupCursor, boolean isExpanded) {
        String kind = groupCursor.getString(groupCursor.getColumnIndex(Constant.COLUMN_GROUP_NAME_KIND));
        mGroupHolder.getTvTitle().setText(kind);
    }

    @Override
    protected View newChildView(Context context, Cursor cursor, boolean isLastChild, ViewGroup parent) {
        if(mChildItemView==null){
            mChildItemView = mInflater.inflate(R.layout.web_item_expand_child,parent,false);
            mChildHolder = new ChildViewHolder(mChildItemView);
        }
        return mChildItemView;
    }

    @Override
    protected void bindChildView(View view, Context context, Cursor cursor, boolean isLastChild) {
        String module = cursor.getString(cursor.getColumnIndex(Constant.COLUMN_CHILD_NAME_MODULE));
        mChildHolder.getTvTitle().setText(module);
    }
}
