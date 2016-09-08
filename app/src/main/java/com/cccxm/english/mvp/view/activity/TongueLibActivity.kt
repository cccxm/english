package com.cccxm.english.mvp.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.cccxm.english.R
import com.cccxm.english.bean.TongueLibBean
import com.cccxm.english.mvp.contract.TongueLibContract
import com.cccxm.english.mvp.model.TongueLibModel
import com.cccxm.english.mvp.present.TongueLibPresenter
import com.cccxm.english.mvp.view.holder.DialogItemHolder
import com.cxm.bind.AbsViewHolder
import com.cxm.bind.ViewInject
import com.cxm.mvp.BaseActivity
import com.cxm.utils.DialogUtils
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
        presenter!!.loadList()
    }

    override fun initView() {
        holder.bind(this)
        val head = layoutInflater.inflate(R.layout.item_tongue_lib, null)
        (head.findViewById(R.id.item_name_tv) as TextView).text = "点击继续背诵"
        holder.lv!!.addHeaderView(head)
        holder.drop!!.drop_top = UnitUtils().dip2px(50F, this)
    }

    override fun register() {
        source.register(holder.drop!!)
        holder.drop.setOnTouchListener { view, motionEvent ->
            source.event(motionEvent)
        }
        holder.lv!!.setOnItemClickListener { parent, view, position, id ->
            presenter!!.click(position)
        }
    }

    override fun showLibInfo(name: String, level: Int, score: Int, my: Int) {
        val dialog = object : DialogUtils(null, R.layout.dialog_item_info) {
            override fun prepare(view: View) {
                val holder = DialogItemHolder()
                holder.bind(view)
                holder.lib_name!!.text = name
                holder.lib_level!!.text = "积分大于 $level 可下载"
                holder.lib_score!!.text = "学习完成后可以获得 $score 积分"
                holder.my_score!!.text = "您当前的积分等级为:$my"
            }
        }
        dialog.show(fragmentManager, "tag")
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