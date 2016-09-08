package com.cxm.utils

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window

/**
 * 对话框工具类
 * 陈小默 16/9/1.
 */
abstract class DialogUtils(val title: String?,
                           val layout: Int = 0,
                           val message: String = "") : DialogFragment() {
    private var positiveName: String? = null
    private var negativeName: String? = null

    fun setPositiveName(name: String): DialogUtils {
        this.positiveName = name
        return this
    }

    fun setNegativeName(name: String): DialogUtils {
        this.negativeName = name
        return this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (layout != 0) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view = inflater.inflate(layout, container, false)
            prepare(view)
            return view
        } else
            return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (layout == 0) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(title)
            builder.setMessage(message)
            if (positiveName != null) {
                builder.setPositiveButton(positiveName, { dialog, which ->
                    setPositive(null)
                })
            }
            if (negativeName != null) {
                builder.setNegativeButton(negativeName, { dialog, which ->
                    setNegative(null)
                })
            }
            return builder.create()
        } else return super.onCreateDialog(savedInstanceState)
    }

    /**
     * 用于指定显示对话框前的操作,此方法获得传入布局的对象
     */
    open fun prepare(view: View) {
    }

    /**
     * 确定按钮点击事件
     */
    open fun setPositive(view: View?) {
        return
    }

    /**
     * 取消按钮点击事件
     */
    open fun setNegative(view: View?) {
        return
    }
}