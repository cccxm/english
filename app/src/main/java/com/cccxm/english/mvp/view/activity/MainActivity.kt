package com.cccxm.english.mvp.view.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.cccxm.english.R
import com.cccxm.english.config.UserHolder
import com.cccxm.english.mvp.contract.MainContract
import com.cccxm.english.mvp.model.MainModel
import com.cccxm.english.mvp.present.MainPresent
import com.cxm.bind.AbsViewHolder
import com.cxm.bind.ViewInject
import com.cxm.mvp.BaseActivity
import com.cxm.utils.ActivityUtils
import com.cxm.utils.UnitUtils
import com.cxm.view.DropEventSource
import com.cxm.view.DropLayout

class MainActivity : BaseActivity<MainContract.IMainPresent>(), MainContract.IMainView {
    val holder = MainViewHolder()
    val dropSource = DropEventSource()
    val user = UserHolder.getUser(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresent(MainModel(), this)
    }

    override fun initView() {
        holder.bind(this)
        holder.score!!.text = "您好!陈小默\r\n您目前的积分是:${user!!.score}"
    }

    override fun register() {
        dropSource.register(holder.dropLayout!!)
        holder.dropLayout.drop_top = UnitUtils().dip2px(50F, this)
        holder.dropLayout.setOnTouchListener { view, motionEvent ->
            dropSource.event(motionEvent)
        }
        holder.dialog!!.setOnClickListener {
            dialogLibActivity()
        }
        holder.word!!.setOnClickListener {
            wordLibActivity()
        }
        holder.tongue!!.setOnClickListener {
            tongueLibActivity()
        }
        holder.settings!!.setOnClickListener {
            settingsActivity()
        }
    }

    override fun wordLibActivity() {
        Toast.makeText(this, "wordLibActivity", Toast.LENGTH_SHORT).show()
    }

    override fun tongueLibActivity() {
        ActivityUtils.startActivity(this, TongueLibActivity::class.java)
    }

    override fun dialogLibActivity() {
        Toast.makeText(this, "dialogLibActivity", Toast.LENGTH_SHORT).show()
    }

    override fun settingsActivity() {
        Toast.makeText(this, "settingsActivity", Toast.LENGTH_SHORT).show()

    }

    override fun context() = this

    override fun finish() {
        dropSource.unregister()
        super.finish()
    }
}

class MainViewHolder : AbsViewHolder() {
    @ViewInject(R.id.drop_group)
    val dropLayout: DropLayout? = null
    @ViewInject(R.id.word_lib)
    val word: View? = null
    @ViewInject(R.id.tongue_lib)
    val tongue: View? = null
    @ViewInject(R.id.dialog_lib)
    val dialog: View? = null
    @ViewInject(R.id.settings)
    val settings: View? = null
    @ViewInject(R.id.score_tv)
    val score: TextView? = null
}
