package com.cccxm.english.mvp.present

import com.cccxm.english.mvp.contract.MainContract

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/30.
 */
class MainPresent(val model: MainContract.IMainModel,
                  val view: MainContract.IMainView) : MainContract.IMainPresent {
    override fun start() {
        view.initView()
        view.register()
    }
}