package com.cccxm.english.mvp.view.activity

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.widget.Button
import android.widget.Toast
import com.cccxm.english.R
import com.cccxm.english.mvp.contract.RegisterContract
import com.cccxm.english.mvp.model.RegisterModel
import com.cccxm.english.mvp.present.RegisterPresenter
import com.cxm.bind.AbsViewHolder
import com.cxm.bind.ViewInject
import com.cxm.mvp.BaseActivity
import com.cxm.utils.ActivityUtils

class RegisterActivity : BaseActivity<RegisterContract.IRegisterPresenter>(), RegisterContract.IRegisterView {

    val holder = RegisterViewHolder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        holder.bind(this)
        presenter = RegisterPresenter(RegisterModel(), this)
        holder.register_bt!!.setOnClickListener { view ->
            presenter!!.register()
        }
        holder.login_bt!!.setOnClickListener { view ->
            loginActivity()
        }
    }

    override fun getUsername() = holder.username_et!!.text.toString()

    override fun getPassword1() = holder.password1_et!!.text.toString()

    override fun getPassword2() = holder.password2_et!!.text.toString()

    override fun errUsername(msg: String) {
        holder.username_et!!.error = msg
    }

    override fun errPassword1(msg: String) {
        holder.password1_et!!.error = msg
    }

    override fun errPassword2(msg: String) {
        holder.password2_et!!.error = msg
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, "$msg", Toast.LENGTH_SHORT).show()
    }

    override fun mainActivity() {
        ActivityUtils.startActivity(this, MainActivity::class.java)
    }

    override fun loginActivity() {
        finish()
    }

    override fun registerState() {
        holder.login_bt!!.isEnabled = false
        holder.register_bt!!.isEnabled = false
        holder.register_bt.text = "正在注册"
    }

    override fun normalState() {
        holder.login_bt!!.isEnabled = true
        holder.register_bt!!.isEnabled = true
        holder.register_bt.text = "注册"
    }

    override fun context() = this
}

class RegisterViewHolder : AbsViewHolder() {
    @ViewInject(R.id.username)
    val username_et: TextInputEditText? = null
    @ViewInject(R.id.password1)
    val password1_et: TextInputEditText? = null
    @ViewInject(R.id.password2)
    val password2_et: TextInputEditText? = null
    @ViewInject(R.id.register_bt)
    val register_bt: Button? = null
    @ViewInject(R.id.login_bt)
    val login_bt: Button? = null
}
