package com.cccxm.english.mvp.view.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.cccxm.english.R
import com.cccxm.english.mvp.contract.ReciteContract
import com.cccxm.english.mvp.model.ReciteModel
import com.cccxm.english.mvp.present.RecitePresenter
import com.cxm.bind.AbsViewHolder
import com.cxm.bind.ViewInject
import com.cxm.mvp.BaseActivity

class ReciteActivity : BaseActivity<ReciteContract.IRecitePresenter>(), ReciteContract.IReciteView {
    private val holder = ReciteHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recite)
        presenter = RecitePresenter(ReciteModel(), this)
    }

    override fun initView() {
        holder.bind(this)
    }

    override fun enable() {
        holder.bad!!.isEnabled = false
        holder.better!!.isEnabled = false
    }

    override fun register() {
        holder.bad!!.setOnClickListener { v ->
            presenter!!.bad()
        }
        holder.better!!.setOnClickListener { v ->
            presenter!!.better()
        }
        holder.en!!.setOnClickListener { v ->
            presenter!!.clickEn()
        }
        holder.ch!!.setOnClickListener { v ->
            presenter!!.clickCh()
        }
    }

    override fun getCounter(): TextView = holder.count!!

    override fun getCHPanel(): TextView = holder.ch!!

    override fun getENPanel(): TextView = holder.en!!

    override fun context() = this

    override fun finish() {
        presenter!!.submit()
        super.finish()
    }
}

class ReciteHolder : AbsViewHolder() {
    @ViewInject(R.id.count_tv)
    val count: TextView? = null
    @ViewInject(R.id.en_tv)
    val en: TextView? = null
    @ViewInject(R.id.ch_tv)
    val ch: TextView? = null
    @ViewInject(R.id.bad)
    val bad: View? = null
    @ViewInject(R.id.better)
    val better: View? = null
}
