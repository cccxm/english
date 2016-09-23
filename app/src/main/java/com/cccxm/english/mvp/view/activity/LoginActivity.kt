package com.cccxm.english.mvp.view.activity

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.widget.Button
import android.widget.Toast
import com.cccxm.english.R
import com.cccxm.english.mvp.contract.LoginContract
import com.cccxm.english.mvp.model.LoginModel
import com.cccxm.english.mvp.present.LoginPresenter
import com.cxm.bind.AbsViewHolder
import com.cxm.bind.ViewInject
import com.cxm.mvp.BaseActivity
import com.cxm.utils.ActivityUtils

class LoginActivity : BaseActivity<LoginContract.ILoginPresent>(), LoginContract.ILoginView {
    private val holder = LoginViewHolder()
    //private var progress: ProgressUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
        holder.bind(this)
        presenter = LoginPresenter(LoginModel(), this)
        //progress = ProgressUtils(this)
        holder.login_bt!!.setOnClickListener { view ->
            presenter!!.login()
        }
        holder.register_bt!!.setOnClickListener { view ->
            registerActivity()
        }
    }

    override fun setUsername(username: String) {
        holder.username_et!!.setText(username)
    }

    override fun setPassword(password: String) {
        holder.password_et!!.setText(password)
    }

    override fun getUsername() = holder.username_et!!.text.toString()

    override fun getPassword() = holder.password_et!!.text.toString()

    override fun usernameError(msg: String) {
        holder.username_et!!.error = msg
    }

    override fun passwordError(msg: String) {
        holder.password_et!!.error = msg
    }

    override fun loginMessage(msg: String) {
        Toast.makeText(this, "$msg", Toast.LENGTH_SHORT).show()
    }

    override fun mainActivity() {
        ActivityUtils.startActivity(this, MainActivity::class.java)
    }

    override fun registerActivity() {
        ActivityUtils.startActivity(this, RegisterActivity::class.java)
    }

    override fun context() = this

    override fun loginStyle() {
        holder.login_bt!!.isEnabled = false
        holder.register_bt!!.isEnabled = false
        holder.login_bt!!.text = "正在登陆"
    }

    override fun normalStyle() {
        holder.login_bt!!.isEnabled = true
        holder.register_bt!!.isEnabled = true
        holder.login_bt!!.text = "登陆"
    }
}

private class LoginViewHolder : AbsViewHolder() {
    @ViewInject(R.id.username)
    val username_et: TextInputEditText? = null
    @ViewInject(R.id.password)
    val password_et: TextInputEditText? = null
    @ViewInject(R.id.login_bt)
    val login_bt: Button? = null
    @ViewInject(R.id.register_bt)
    val register_bt: Button? = null
}
