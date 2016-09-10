package com.cccxm.english.mvp.present

import android.os.Handler
import android.os.Message
import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.bean.WelcomeBean
import com.cccxm.english.config.UserHolder
import com.cccxm.english.mvp.contract.WelcomeContract
import com.cxm.utils.StringUtils
import com.squareup.picasso.Picasso

/**
 * 陈小默 16/8/29.
 */
class WelcomePresent(val model: WelcomeContract.IWelcomeModel,
                     val view: WelcomeContract.IWelcomeView) : WelcomeContract.IWelcomePresent {

    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 0) {
                login()
            } else {
                view.timer(msg.what)
                sendEmptyMessageDelayed(msg.what - 1, 1000L)
            }
        }
    }

    override fun start() {
        model.loadData(this, { res ->
            callback(res)
        })
    }


    fun callback(response: HttpResponse<WelcomeBean>) {
        if (response.isSuccess) {
            view.getImage().setOnClickListener { v ->
                view.openBrowser(response.data.url)
            }
            Picasso.with(view.context()).load(response.data.img).into(view.getImage())
            handler.sendEmptyMessage(3)
        } else {
            login()
        }
    }

    override fun skip() {

    }

    private fun login() {
        val user = UserHolder.getUser(view.context())
        if (user == null) {
            view.startLoginActivity()
        } else {
            if (StringUtils.isPhone(user.username) && StringUtils.password(user.password)) {
                model.login(user.username, user.password, { res ->
                    if (res.isSuccess) {
                        val bean = res.data
                        bean.password = user.password
                        UserHolder.saveUser(view.context(), bean)
                        view.startMainActivity()
                        view.finish()
                    } else {
                        view.startLoginActivity()
                        view.finish()
                    }
                })
            } else {
                view.startLoginActivity()
                view.finish()
            }
        }
    }
}