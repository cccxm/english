package com.cccxm.english.mvp.contract

import android.gesture.Gesture
import android.widget.TextView
import com.cxm.mvp.IPresenter
import com.cxm.mvp.IView

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/26.
 */
interface SafeContract {
    interface ISafeView : IView {
        fun register()
        fun notice(): TextView
        fun toast(msg: String)
        fun welcome()
    }

    interface ISafePresenter : IPresenter {
        fun log(gesture: Gesture)
    }
}