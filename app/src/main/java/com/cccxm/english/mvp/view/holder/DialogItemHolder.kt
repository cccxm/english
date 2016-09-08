package com.cccxm.english.mvp.view.holder

import android.widget.TextView
import com.cccxm.english.R
import com.cxm.bind.AbsViewHolder
import com.cxm.bind.ViewInject

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/1.
 */
class DialogItemHolder : AbsViewHolder() {
    @ViewInject(R.id.lib_name)
    val lib_name: TextView? = null
    @ViewInject(R.id.lib_level)
    val lib_level: TextView? = null
    @ViewInject(R.id.lib_score)
    val lib_score: TextView? = null
    @ViewInject(R.id.my_score)
    val my_score: TextView? = null
    @ViewInject(R.id.lib_download)
    val lib_download: TextView? = null
}