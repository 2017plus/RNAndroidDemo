package com.rnandroid;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;


/**
 * Created by duxiwei on 18-8-19.
 * Mail  duxiwei@aliyun.com
 */
public class ReactCommModule extends ReactContextBaseJavaModule {
    ReactApplicationContext reactContext ;
    public ReactCommModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactCommModule";
    }

    @ReactMethod
    public void startActivityFromJS(String name, String msg) throws ClassNotFoundException {
        Activity activity = getCurrentActivity();
        Class toActivity = Class.forName(name);
        Intent intent = new Intent(activity,toActivity);
        intent.putExtra("msg",msg);
        activity.startActivity(intent);
    }

    /**
     * 向RN发送消息
     * @param msg
     */
    public void sendMsgToRN(String name, String msg) {
        Log.e("---","sendMsgToRN");
        this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(name,msg);
    }

    /**
     * Callback 方式
     * rn调用Native,并获取返回值
     * @param msg
     * @param callback
     */
    @ReactMethod
    public void rnCallNativeFromCallback(String msg, Callback callback) {

        // 1.处理业务逻辑...
        String result = "处理结果：" + msg;
        // 2.回调RN,即将处理结果返回给RN
        callback.invoke(result);
    }

    /**
     * Promise
     * @param msg
     * @param promise
     */
    @ReactMethod
    public void rnCallNativeFromPromise(String msg, Promise promise) {

        Log.e("---","adasdasda");
        // 1.处理业务逻辑...
        String result = "处理结果：" + msg;
        // 2.回调RN,即将处理结果返回给RN
        promise.resolve(result);
    }

}
