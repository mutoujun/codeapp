package com.example.module_web.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_web.R;
import com.example.module_web.adapter.DBAdapter;
import com.example.module_web.db.DBOpenHelper;
import com.example.module_web.db.DBWrapper;
import com.example.module_web.public_data.Constant;
import com.example.module_web.utils.ChildViewHolder;
import com.example.module_web.utils.GroupViewHolder;


/**
 * Created by Administrator on 2018/2/10.
 */

public class WebLeftMenu extends LinearLayout {
    private static final String TAG = "bug";
    //    private final WebLeftMenuBinding mBinding;
    private Context mContext;
    private final View mView;
    private ExpandableListView mExpandList;

    public ExpandableListView getExpandList() {
        return mExpandList;
    }


    @SuppressLint("InflateParams")
    public WebLeftMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.web_menu_left, null, false);
//        mBinding = DataBindingUtil.inflate(inflater,R.layout.web_menu_left,null,false);
//        addView(mBinding.getRoot());
        addView(mView);
        initView();
        init();
    }

    private void initView() {
        mExpandList = mView.findViewById(R.id.expand_list);
}

    private void init() {
        setupExpandableListView();
    }



    private void setupExpandableListView() {
//        loadDB();
        loadLocal();
//        loadNet();
    }
    /**
     * 加载网络数据
     */
    private void loadNet() {

    }

    /**
     * 加载数据库数据
     */
    private void loadDB() {

        DBWrapper dbWrapper = new DBWrapper(mContext);
//        dbWrapper.dropTable("group");
//        dbWrapper.dropTable("child");
        dbWrapper.dropTable("group_data");
        dbWrapper.dropTable("child_data");

        /**
         * 添加数据
         */
        for (String group:Constant.GROUPS) {
            dbWrapper.insertGroup(group,group);
        }
        for (int i = 0; i < Constant.CHILDREN.length; i++) {
            String type = Constant.GROUPS[i];
            Log.e(TAG, "loadDB: type = "+type);
            for (int j = 0; j < Constant.CHILDREN[i].length; j++) {
                String child = Constant.CHILDREN[i][j];
                Log.e(TAG, "loadDB: child = "+ child );
                dbWrapper.insertChild(child,type);
            }
        }

        /**
         * 选取数据
         */
        Cursor groupCursor = dbWrapper.selectFromGroup();
        DBAdapter dbAdapter = new DBAdapter(groupCursor,mContext,dbWrapper);
        mExpandList.setAdapter(dbAdapter);
    }
    /**
     * 加载本地数据
     */
    private void loadLocal() {
        //        mBinding.expandList.setGroupIndicator(null);
        final ExpandAdapter expandAdapter = new ExpandAdapter(mContext, Constant.GROUPS,Constant.CHILDREN);
        mExpandList.setAdapter(expandAdapter);
    }

}


class ExpandAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "Adapter";

    private Context context;
    private LayoutInflater inflater;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            notifyDataSetChanged();
            super.handleMessage(msg);
        }
    };

    private String[] groupStrings;
    private String[][] childStrings;


    ExpandAdapter(Context context, String[] groupStrings, String[][] childStrings){
        this.context=context;
        this.groupStrings = groupStrings;
        this.childStrings = childStrings;
        inflater = LayoutInflater.from(context);
    }
    public void refresh() {
        handler.sendMessage(new Message());
    }

    public String getChild(int groupPosition, int childPosition) {
        return childStrings[groupPosition][childPosition];
    }

    public long getChildId(int groupPosition, int childPosition) {
        int offset = 0;
        for (int i = 0; i < groupPosition; i++) {
            offset = offset + childStrings[i].length;
        }
        return childPosition+offset;
    }

    @SuppressLint("InflateParams")
    public View getChildView(int groupPosition, final int childPosition, boolean arg2, View convertView,
                             ViewGroup arg4) {
        ChildViewHolder childHolder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.web_item_expand_child, null);
            childHolder = new ChildViewHolder(convertView);
            convertView.setTag(childHolder);
        }else {
            childHolder = (ChildViewHolder) convertView.getTag();
        }
        childHolder.getTvTitle().setText(childStrings[groupPosition][childPosition]);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return childStrings[groupPosition].length;
    }


    public String getGroup(int groupPosition) {
        return groupStrings[groupPosition];
    }

    public int getGroupCount() {
        return groupStrings.length;
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @SuppressLint("InflateParams")
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup arg3) {
        GroupViewHolder groupHolder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.web_item_expand_group, null);
            groupHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupViewHolder) convertView.getTag();
        }
        //[组号]
        groupHolder.getTvTitle().setText(groupStrings[groupPosition]);
//        ////展开时的图标
//        groupHolder.mImage.setImageResource();
//        //更换展开分组图片
//        if(!isExpanded){
//            groupHolder.mImage.setImageResource();//合并时的图标
//        }
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }
    // 子选项是否可以选择
    public boolean isChildSelectable(int arg0, int arg1) {
    // TODO Auto-generated method stub
        return true;
    }
}


