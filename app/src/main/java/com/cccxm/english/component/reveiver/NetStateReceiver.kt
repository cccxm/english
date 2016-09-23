package com.cccxm.english.component.reveiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.cccxm.english.config.NetState
import com.cxm.utils.NetUtils

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/22.
 */
class NetStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val net = NetUtils(context)
        val state = net.NETWORK_TYPE
        if (NetState.state != state) {
            NetState.state = net.NETWORK_TYPE
            Log.e("NetState", "${NetState.state.name}")
        }
    }
}