package com.cccxm.english.mvp.present

import android.gesture.Gesture
import com.cccxm.english.mvp.contract.SafeContract
import com.cccxm.english.mvp.model.GestureModel

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/26.
 */
class SafePresenter(val view: SafeContract.ISafeView,
                    val model: GestureModel) : SafeContract.ISafePresenter {
    override fun log(gesture: Gesture) {
        if (model.recognizeSafe(gesture)) {
            view.welcome()
        } else {
            view.toast("手势不正确,请重新输入")
        }
    }

    override fun start() {
        if (model.switch)
            view.register()
        else
            view.welcome()
    }
}