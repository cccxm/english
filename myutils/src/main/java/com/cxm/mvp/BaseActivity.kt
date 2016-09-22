package com.cxm.mvp

import android.content.pm.ActivityInfo
import android.content.res.Resources
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
    var allowActionBar = false
    var orientation = Orientation.PORTRAIT
    var presenter: P? = null
        set(value) {
            value?.start()
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = orientation.value
        if (allowFullScreen)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (!allowActionBar)
            supportActionBar?.hide()
        ActivityUtils.registerActivity(this)
    }

    override fun finish() {
        presenter = null
        ActivityUtils.unregisterActivity(this)
        super.finish()
    }

    val root: View get() = (findViewById(android.R.id.content) as ViewGroup).getChildAt(0)

    val height: Int get() = resources.displayMetrics.heightPixels

    val width: Int get() = resources.displayMetrics.widthPixels

    enum class Orientation(val value: Int) {
        PORTRAIT(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT),
        LANDSCAPE(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE),
        AUTO(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}