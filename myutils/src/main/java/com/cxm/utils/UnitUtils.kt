package com.cxm.utils

import android.content.Context

/**
 * 单位转换工具类
 * 陈小默 16/8/19.
 */
class UnitUtils {
    fun px2dip(pxValue: Int, context: Context) = pxValue / density(context) + 0.5

    fun dip2px(dipValue: Float, context: Context) = (density(context) * dipValue + 0.5F).toInt()

    fun density(context: Context) = context.resources.displayMetrics.density
}