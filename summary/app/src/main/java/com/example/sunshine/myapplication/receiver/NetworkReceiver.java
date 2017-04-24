package com.example.sunshine.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by liyu on 2016/9/14.
 */
public class NetworkReceiver extends BroadcastReceiver {
    private ConnectivityManager mConnectivityManager;
    private NetworkInfo netInfo;
    private Context mContext;
    private INetWorkReceiver listener;

    public NetworkReceiver(Context context){
        this.mContext = context;
    }

    public void registerReceiver(INetWorkReceiver listener){
        this.listener = listener;
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(this, filter);
    }

    public void unRegisterReceiver(){
        mContext.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            netInfo = mConnectivityManager.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isAvailable()) {

                listener.onNetConnected();
            }else {

                listener.onNetDisConnected();
            }
        } else {

            listener.onNetDisConnected();
        }
    }

}
