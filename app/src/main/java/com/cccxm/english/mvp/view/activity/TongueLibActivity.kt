package com.cccxm.english.mvp.view.activity

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.cccxm.english.R
import com.cccxm.english.bean.TongueLibBean
import com.cccxm.english.mvp.contract.TongueLibContract
import com.cccxm.english.mvp.model.TongueLibModel
import com.cccxm.english.mvp.present.TongueLibPresenter
import com.cxm.bind.AbsViewHolder
import com.cxm.bind.ViewInject
import com.cxm.mvp.BaseActivity
import com.cxm.utils.UnitUtils
import com.cxm.view.DropEventSource
import com.cxm.view.DropLayout

class TongueLibActivity : BaseActivity<TongueLibContract.ITongueLibPresenter>(), TongueLibContract.ITongueLibView {

    private val holder = TongueLibActivityViewHolder()
    private val source = DropEventSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tongue_lib)
        presenter = TongueLibPresenter(TongueLibModel(), this)
        presenter!!.loadList(0)
    }

    override fun initView() {
        holder.bind(this)
        holder.drop!!.drop_top = UnitUtils().dip2px(50F, this)
    }

    override fun register() {
        source.register(holder.drop!!)
        holder.drop.setOnTouchListener { view, motionEvent -> source.event(motionEvent) }
    }

    override fun startDetailActivity(bean: TongueLibBean) {
    }

    override fun message(msg: String) {
        Toast.makeText(this, "$msg", Toast.LENGTH_SHORT).show()
    }

    override fun getListView(): ListView = holder.lv!!

    override fun context() = this
}

class TongueLibActivityViewHolder : AbsViewHolder() {
    @ViewInject(R.id.tongue_lv)
    val lv: ListView? = null
    @ViewInject(R.id.dropLayout)
    val drop: DropLayout? = null
}