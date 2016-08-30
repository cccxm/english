package com.cccxm.english.mvp.contract

import com.cxm.mvp.IPresenter
import com.cxm.mvp.IView

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/30.
 */
interface MainContract {
    interface IMainView : IView {
        fun initView()
        fun register()
        fun wordLibActivity()
        fun tongueLibActivity()
        fun dialogLibActivity()
        fun settingsActivity()
    }

    interface IMainPresent : IPresenter {

    }

    interface IMainMedel {

    }
}