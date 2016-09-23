package com.cccxm.english.mvp.view.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import com.cccxm.english.R
import com.cccxm.english.mvp.contract.WelcomeContract
import com.cccxm.english.mvp.model.WelcomeModel
import com.cccxm.english.mvp.present.WelcomePresent
import com.cxm.mvp.BaseActivity
import com.cxm.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_launch.*

class WelcomeActivity : BaseActivity<WelcomeContract.IWelcomePresent>(), WelcomeContract.IWelcomeView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        presenter = WelcomePresent(WelcomeModel(), this)
    }

    override fun startMainActivity() {
        ActivityUtils.startActivity(this, MainActivity::class.java)
        finish()
    }

    override fun startLoginActivity() {
        ActivityUtils.startActivity(this, LoginActivity::class.java)
        finish()
    }

    override fun getImage(): ImageView = welcomeAdIV

    override fun timer(time: Int): Unit {
        welcomeTimeTv.text = "$time 秒后進入"
    }

    override fun context(): Context = this

    override fun openBrowser(url: String) {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        val uri = Uri.parse(url)
        intent.data = uri
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity")
        startActivity(intent)
    }
}
