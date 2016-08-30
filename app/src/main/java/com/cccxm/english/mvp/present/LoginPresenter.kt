package com.cccxm.english.mvp.present

import com.cccxm.english.config.UserHolder
import com.cccxm.english.mvp.contract.LoginContract
import com.cxm.utils.ActivityUtils
import com.cxm.utils.StringUtils

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
class LoginPresenter(val model: LoginContract.ILoginModel,
                     val view: LoginContract.ILoginView) : LoginContract.ILoginPresent {

    override fun login() {
        val username = view.getUsername()
        val password = view.getPassword()
        if (!StringUtils.isPhone(username)) {
            view.usernameError("用户名必须是手机号码")
        } else if (!StringUtils.password(password)) {
            view.passwordError("密码是长度6-18位的字母和数字的组合")
        } else {
            view.loginStyle()
            model.login(username, password, { res ->
                view.normalStyle()
                if (res.isSuccess) {
                    res.data.password = password
                    UserHolder.saveUser(view.context(), res.data)
                    view.loginMessage("登录成功")
                    ActivityUtils.finishActivities {
                        view.mainActivity()
                    }
                } else {
                    view.loginMessage(res.message)
                }
            })
        }
    }

    override fun start() {
    }
}