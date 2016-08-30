package com.cxm.mvp

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.cxm.utils.ActivityUtils

/**
 * MVP模式抽象Activity基類
 * 陈小默 16/8/19.
 */
abstract class BaseActivity<P : IPresenter> : AppCompatActivity() {
    var allowFullScreen = false
    var presenter: P? = null
        set(value) {
            value?.start()
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //Vertical Screen
        if (allowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        ActivityUtils.registerActivity(this)
    }

    override fun finish() {
        presenter = null
        ActivityUtils.unregisterActivity(this)
        super.finish()
    }

    fun getRootView(): View = (findViewById(android.R.id.content) as ViewGroup).getChildAt(0)

    fun getHeightPixels() = resources.displayMetrics.heightPixels

    fun getWidthPixels() = resources.displayMetrics.widthPixels
}