package com.cccxm.english.mvp.view.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.cccxm.english.R
import com.cccxm.english.mvp.contract.WelcomeContract
import com.cccxm.english.mvp.model.WelcomeModel
import com.cccxm.english.mvp.present.WelcomePresent
import com.cxm.bind.AbsViewHolder
import com.cxm.bind.ViewInject
import com.cxm.mvp.BaseActivity
import com.cxm.utils.ActivityUtils

class WelcomeActivity : BaseActivity<WelcomeContract.IWelcomePresent>(), WelcomeContract.IWelcomeView {
    private val holder = WelcomeHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        holder.bind(this)
        presenter = WelcomePresent(WelcomeModel(), this)
    }

    override fun startMainActivity() {
        ActivityUtils.startActivity(this, MainActivity::class.java)
    }

    override fun startLoginActivity() {
        ActivityUtils.startActivity(this, LoginActivity::class.java)
    }

    override fun getImage(): ImageView = holder.adImage_iv!!

    override fun timer(time: Int): Unit {
        holder.adTimer_tv!!.text = "$time 秒后進入"
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

class WelcomeHolder : AbsViewHolder() {
    @ViewInject(R.id.welcome_advertisement)
    var adImage_iv: ImageView? = null
    @ViewInject(R.id.welcome_timer)
    var adTimer_tv: TextView? = null
}
