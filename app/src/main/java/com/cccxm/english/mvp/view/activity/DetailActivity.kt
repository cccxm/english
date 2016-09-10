package com.cccxm.english.mvp.view.activity

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import com.cccxm.dao.Tongue
import com.cccxm.dao.TongueDao
import com.cccxm.english.R
import com.cccxm.master.DBMaster
import com.cxm.bind.AbsViewHolder
import com.cxm.bind.ViewInject
import com.cxm.mvp.BaseActivity
import com.cxm.mvp.IPresenter
import com.cxm.view.adapter.CommonBaseAdapter

class DetailActivity : BaseActivity<IPresenter>() {
    val adapter: CommonBaseAdapter<Tongue, DetailItemHolder>

    init {
        adapter = object : CommonBaseAdapter<Tongue, DetailItemHolder>(DetailItemHolder::class.java, R.layout.item_detail, this) {
            override fun viewHolder(holder: DetailItemHolder, item: Tongue, position: Int) {
                holder.ch!!.text = item.ch
                holder.en!!.text = item.en
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.getLongExtra("id", 0L)
        if (id == 0L) {
            finish()
        } else {
            val lv = findViewById(R.id.detail_lv) as ListView
            lv.adapter = adapter
            val session = DBMaster.newSession()
            val list = session.tongueDao.queryBuilder().where(TongueDao.Properties.Lib_id.eq(id)).build().list()
            adapter.list = list
        }
    }
}

class DetailItemHolder : AbsViewHolder() {
    @ViewInject(R.id.ch_tv)
    var ch: TextView? = null
    @ViewInject(R.id.en_tv)
    var en: TextView? = null
}
