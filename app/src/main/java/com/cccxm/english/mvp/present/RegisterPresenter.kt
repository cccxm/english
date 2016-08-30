package com.cccxm.english.mvp.present

import com.cccxm.english.config.UserHolder
import com.cccxm.english.mvp.contract.RegisterContract
import com.cxm.utils.ActivityUtils
import com.cxm.utils.StringUtils

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
class RegisterPresenter(val model: RegisterContract.IRegisterModel,
                        val view: RegisterContract.IRegisterView) : RegisterContract.IRegisterPresenter {
    override fun start() {

    }

    override fun register() {
        val username = view.getUsername()
        val password1 = view.getPassword1()
        val password2 = view.getPassword2()
        if (!StringUtils.isPhone(username)) {
            view.errUsername("用户名必须是手机号")
        } else if (!StringUtils.password(password1)) {
            view.errPassword1("密码必须是6-18位字母或数字的组合")
        } else if (!password1.equals(password2)) {
            view.errPassword2("两次密码输入必须一致")
        } else {
            view.registerState()
            model.register(username, password1, { res ->
                view.normalState()
                if (res.isSuccess) {
                    res.data.password = password1
                    UserHolder.saveUser(view.context(), res.data)
                    view.showMessage("注册成功")
                    ActivityUtils.finishActivities {
                        view.mainActivity()
                    }
                } else {
                    view.showMessage(res.message)
                }
            })
        }
    }
}