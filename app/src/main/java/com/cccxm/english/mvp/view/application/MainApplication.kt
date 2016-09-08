package com.cccxm.english.mvp.view.application

import com.cccxm.master.DBMaster
import com.cxm.myutils.MyApplication

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
    }
}