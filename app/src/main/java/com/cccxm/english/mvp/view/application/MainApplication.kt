package com.cccxm.english.mvp.view.application

import android.content.IntentFilter
import android.net.ConnectivityManager
import com.cccxm.english.component.reveiver.NetStateReceiver
import com.cccxm.english.config.NetState
import com.cccxm.master.DBMaster
import com.cxm.myutils.MyApplication
import com.cxm.utils.NetUtils

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/1.
 */
class MainApplication : MyApplication() {
    override fun onCreate() {
        super.onCreate()
        DBMaster.init(this, "English")
        NetState.state = NetUtils(this).NETWORK_TYPE
        val receiver = NetStateReceiver()
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiver, filter)
    }
}