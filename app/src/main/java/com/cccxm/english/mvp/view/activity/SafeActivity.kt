package com.cccxm.english.mvp.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.cccxm.english.R
import com.cccxm.english.mvp.contract.SafeContract
import com.cccxm.english.mvp.model.GestureModel
import com.cccxm.english.mvp.present.SafePresenter
import com.cxm.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_safe.*

class SafeActivity : BaseActivity<SafeContract.ISafePresenter>(), SafeContract.ISafeView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe)
        presenter = SafePresenter(this, GestureModel(this))
    }

    override fun register() {
        as_gov_log.addOnGesturePerformedListener { gestureOverlayView, gesture ->
            presenter!!.log(gesture)
        }
    }

    override fun welcome() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

    override fun notice(): TextView = as_notice

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun context(): Context = this
}
