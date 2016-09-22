package com.cccxm.english.component.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/22.
 */
class NetStateBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("TAG","net state changed")
        val connManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if(wifi==NetworkInfo.State.CONNECTED){

        }
    }
}