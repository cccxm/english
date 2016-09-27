package com.cccxm.english.mvp.view.activity

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.cccxm.english.R
import com.cccxm.english.mvp.contract.LogGestureContract
import com.cccxm.english.mvp.model.GestureModel
import com.cccxm.english.mvp.present.LogGesturePresenter
import com.cxm.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_log_gesture.*

class LogGestureActivity : BaseActivity<LogGestureContract.ILogGesturePresenter>(), LogGestureContract.ILogGestureView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_gesture)
        presenter = LogGesturePresenter(this, GestureModel(this))
    }

    override fun register() {
        alg_gov_log.addOnGesturePerformedListener { gestureOverlayView, gesture ->
            presenter!!.log(gesture)
        }
    }

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun notice(): TextView = alg_notice

    override fun context(): Context = this
}
