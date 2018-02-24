package com.example.module_web.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/2/9.
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {
    public B mB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mB = DataBindingUtil.setContentView(this,setLayoutId());
        init(mB,savedInstanceState);
    }

    protected abstract void init(B b,@Nullable Bundle savedInstanceState);

    protected abstract int setLayoutId();

    public void toast(String info){
        Toast.makeText(this,info,Toast.LENGTH_SHORT).show();
    }

    public void toast(int info){
        Toast.makeText(this,String.valueOf(info),Toast.LENGTH_SHORT).show();
    }

    public void toast(float info){
        Toast.makeText(this,String.valueOf(info),Toast.LENGTH_SHORT).show();
    }
}
