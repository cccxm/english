package com.cccxm.english.config

import android.content.Context
import com.cccxm.english.bean.UserBean
import com.cxm.utils.BeanUtils

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
object UserHolder {
    private var user: UserBean? = null
    fun getUser(context: Context): UserBean? {
        if (user == null)
            user = BeanUtils().deSerialized(context, UserBean::class.java)
        return user
    }

    fun getUser(): UserBean? {
        return user
    }

    fun saveUser(context: Context, user: UserBean) {
        this.user = user
        BeanUtils().serialized(context, user)
    }
}