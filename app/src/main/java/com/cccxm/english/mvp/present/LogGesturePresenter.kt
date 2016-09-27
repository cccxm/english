package com.cccxm.english.mvp.present

import android.gesture.Gesture
import com.cccxm.english.R
import com.cccxm.english.mvp.contract.LogGestureContract
import com.cccxm.english.mvp.model.GestureModel

class LogGesturePresenter(val view: LogGestureContract.ILogGestureView,
                          val model: GestureModel) : LogGestureContract.ILogGesturePresenter {

    var first = true

    override fun start() {
        view.register()
    }

    override fun log(gesture: Gesture) {
        if (first) {
            //这是第一次
            first = false
            model.temp(gesture)
            view.notice().setText(R.string.alg_notice_second)
        } else {
            //这是第二次
            if (model.recognizeTemp(gesture)) {
                model.save(gesture)
                view.toast("手势密码设置成功")
                view.finish()
            } else {
                view.notice().setText(R.string.alg_notice_reFirst)
                first = true
            }
        }
    }
}