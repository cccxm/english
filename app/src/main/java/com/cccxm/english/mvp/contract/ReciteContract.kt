package com.cccxm.english.mvp.contract

import android.widget.TextView
import com.cccxm.dao.Tongue
import com.cccxm.dao.TongueLib
import com.cxm.mvp.IPresenter
import com.cxm.mvp.IView

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/9.
 */
interface ReciteContract {
    interface IReciteView : IView {
        fun initView()
        fun register()
        fun getCHPanel(): TextView
        fun getENPanel(): TextView
        fun getCounter(): TextView
        fun enable()
    }

    interface IRecitePresenter : IPresenter {
        fun better()
        fun bad()
        fun next()
        fun submit()
        fun clickEn()
        fun clickCh()
    }

    interface IReciteModel {
        fun random(): MutableList<Tongue>
        fun current(): MutableList<Tongue>
        fun upgrade(item: Tongue)
        fun submit(lib: TongueLib, token: String)
        fun LibList(): MutableList<TongueLib>
        fun tongueList(lib_id: Long): MutableList<Tongue>
    }
}