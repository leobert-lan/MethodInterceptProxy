package com.mdit.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.mdit.example.test.Test;

import java.lang.reflect.Method;

import osp.leobert.android.proxy.CallbackFilter;
import osp.leobert.android.proxy.EnhancerProxy;
import osp.leobert.android.proxy.method.MethodProxy;
import osp.leobert.android.proxy.method.interceptor.MethodInterceptor;
import osp.leobert.android.proxy.method.interceptor.NoOp;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click1(View v) {

        try {

            EnhancerProxy enhancerProxy = new EnhancerProxy(MainActivity.this);
            enhancerProxy.setSuperclass(Test.class);
            enhancerProxy.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object object, Object[] args, MethodProxy methodProxy) throws Exception {
                    Log.e("TAG", "intercept  -- before---");
                    Object obj = methodProxy.invokeSuper(object, args);
                    Log.e("TAG", "intercept  -- after---");
                    return obj;
                }
            });
            Test test = (Test) enhancerProxy.create();

            test.toast2(MainActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void click2(View v) {

        EnhancerProxy enhancerProxy = new EnhancerProxy(this);
        enhancerProxy.setSuperclass(Test.class);
        enhancerProxy.setCallbacks(new MethodInterceptor[]{NoOp.INSTANCE, new MethodInterceptor() {
            @Override
            public Object intercept(Object object, Object[] args, MethodProxy methodProxy) throws Exception {
                Log.e("TAG", "intercept  -- before---");
                Object obj = methodProxy.invokeSuper(object, args);
                Log.e("TAG", "intercept  -- after---");
                return obj;
            }
        }
        });
        enhancerProxy.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if (method.getName().equals("toast2"))
                    return 1;
                return 0;
            }
        });
        Test test = (Test) enhancerProxy.create();

        test.toast3(this);
    }
}
