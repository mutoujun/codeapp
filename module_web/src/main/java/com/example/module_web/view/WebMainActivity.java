package com.example.module_web.view;



import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_web.R;
import com.example.module_web.adapter.MainBarAdapter;
import com.example.module_web.beans.TitleBar;
import com.example.module_web.public_data.Constant;
import com.example.module_web.view.fragment.Fragment_00;
import com.example.module_web.view.fragment.Fragment_01;
import com.example.module_web.view.fragment.Fragment_02;
import com.example.module_web.view.fragment.Fragment_03;
import com.example.module_web.view.fragment.Fragment_10;
import com.example.module_web.view.fragment.Fragment_home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WebMainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "bug";
    private WebLeftMenu mLeftMenu;
    private String[][] mChildStrs;
    private String[] mGroupStrs;
    private Button mOperation;
    private RecyclerView mRecyclerView;
    private String mCurrentTitle = "";
    private ArrayList<String> mHasTitles;
    private MainBarAdapter mBarAdapter;
    private Toolbar mToolbar;
    private DrawerLayout mDrawLayout;
    private Fragment mCurrentFragment;
    private FragmentManager mManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity_main);
        initView();
        initEvent();
        Log.e(TAG, "onCreate: 创建活动");
    }

    private void initEvent() {
        setupLeftMenuClickEvent();
        setupToolbar();
        setupBarRecyclerView();
    }

    private void setupToolbar() {
//        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setupBarRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ArrayList<TitleBar> titleBars = new ArrayList<>();
        titleBars.add(new TitleBar(Constant.MAIN_HOME));

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mBarAdapter = new MainBarAdapter(this,titleBars);
        mBarAdapter.setOnItemClickListener(new MainBarAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(View v, int position,String title) {
                addFragment(title);
            }

            @Override
            public void onItemClick(View v, int position, String title,String lastTitle) {
                removeFragment(title);
                addFragment(lastTitle);
                mBarAdapter.removeItem(position);
                mHasTitles.remove(title);
            }
        });
        mRecyclerView.setAdapter(mBarAdapter);

        addFragment(Constant.MAIN_HOME);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //生成选项菜单
        getMenuInflater().inflate(R.menu.menu_operation,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //设置选项菜单的菜单项的点击事件
        switch (item.getItemId()){
            case R.id.refresh:
                break;
            case R.id.close_current:
                break;
            case R.id.close_other:
                break;
            case R.id.close_all:
                break;
        }
        return true;
    }

    private void setupLeftMenuClickEvent() {
        mChildStrs = Constant.CHILDREN;
        mGroupStrs = Constant.GROUPS;
        mHasTitles = new ArrayList<>();
        mLeftMenu.getExpandList().setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String groupStr = mGroupStrs[groupPosition];
                String childStr = mChildStrs[groupPosition][childPosition];
                //将双级列表的二级标题添加到recyclerView的子项中
                addToTitleBar(childStr);
                //将双级列表的标题添加到toolbar的主标题和副标题上
                updateToolbarTitle(groupStr,childStr);
                addFragment(childStr);
                mHasTitles.add(mCurrentTitle);
                mDrawLayout.closeDrawer(GravityCompat.START);//关闭滑动菜单
                return true;
            }
        });
    }

    private void addFragment(String title) {
        mManager = getSupportFragmentManager();
        FragmentTransaction transaction = mManager.beginTransaction();
        Fragment fragment;

        if(mCurrentTitle.equals(title))
            return;
        mCurrentTitle = title;

        if(mCurrentFragment!=null){
            transaction.hide(mCurrentFragment);
        }
        fragment = mManager.findFragmentByTag(title);

        if(fragment!=null){
            mCurrentFragment = fragment;
            transaction.show(mCurrentFragment);
        }else {
            transaction.add(R.id.web_container,getFragment(title),title);
        }
        transaction.commitAllowingStateLoss();
    }

    private void removeFragment(String title) {
        FragmentTransaction transaction;
        Fragment fragment;

        transaction = mManager.beginTransaction();
        fragment = mManager.findFragmentByTag(title);
        transaction.remove(fragment);
        mCurrentFragment = null;
        transaction.commitAllowingStateLoss();
    }

    private Fragment getFragment(String title) {
        if(title.equals(Constant.MAIN_HOME)){
            mCurrentFragment = new Fragment_home();
        }else if(title.equals(mChildStrs[0][0])){
            mCurrentFragment = new Fragment_00();
        }else if (title.equals(mChildStrs[0][1])){
            mCurrentFragment = new Fragment_01();
        }else if(title.equals(mChildStrs[0][2])){
            mCurrentFragment = new Fragment_02();
        }else if(title.equals(mChildStrs[0][3])){
            mCurrentFragment = new Fragment_03();
        }else if(title.equals(mChildStrs[1][0])){
            mCurrentFragment = new Fragment_10();
        }
        return mCurrentFragment;
    }

    private void updateToolbarTitle(String groupStr, String childStr) {
        String title = (String) mToolbar.getTitle();
        String subtitle = (String) mToolbar.getSubtitle();
        if(title==null||!title.equals(groupStr)){
            mToolbar.setTitle(groupStr);
        }
        if (subtitle==null||!subtitle.equals(childStr)){
            mToolbar.setSubtitle(childStr);
        }
    }

    private void addToTitleBar(String childStr) {
        for(String title:mHasTitles){
            if(title.equals(childStr)) return;
        }
        mBarAdapter.addItem(new TitleBar(childStr));
    }

    private void initView() {
        mLeftMenu = findViewById(R.id.web_left_menu);
        mOperation = findViewById(R.id.web_btn_operation);
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.web_recycler_main_bar);
        mDrawLayout = findViewById(R.id.drawer_layout);
    }

    /**
     * 右上角操作按钮点击事件
     * @param view
     */
    public void onOperationClick(View view) {
        //实例化PopupMenu
        PopupMenu popupMenu = new PopupMenu(this,view);
        //加载menu资源
//        getMenuInflater().inflate(R.menu.menu_operation,popupMenu.getMenu());
        popupMenu.inflate(R.menu.menu_operation);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.refresh:
                        Toast.makeText(WebMainActivity.this,"刷新",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.close_current:
                        Toast.makeText(WebMainActivity.this,"关闭当前",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.close_other:
                        Toast.makeText(WebMainActivity.this,"关闭其他",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.close_all:
                        Toast.makeText(WebMainActivity.this,"关闭所有",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        //显示弹出菜单
        popupMenu.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }




}
