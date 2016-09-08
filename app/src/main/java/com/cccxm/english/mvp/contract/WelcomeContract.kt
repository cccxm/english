package com.cccxm.english.mvp.contract

import android.widget.ImageView
import com.cccxm.english.bean.HttpResponse
import com.cccxm.english.bean.UserBean
import com.cccxm.english.bean.WelcomeBean
import com.cxm.mvp.IPresenter
import com.cxm.mvp.IView

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
interface WelcomeContract {
    interface IWelcomePresent : IPresenter {
        /**
         * 用戶選擇跳過廣告
         */
        fun skip()
    }

    interface IWelcomeView : IView {
        /**
         * 已登錄,打開主頁
         */
        fun startMainActivity()

        /**
         * 未登錄,打開登錄頁
         */
        fun startLoginActivity()

        /**
         * 獲得要顯示圖片的imageView
         */
        fun getImage(): ImageView

        /**
         * 倒計時
         */
        fun timer(time: Int)

        /**
         * 打開瀏覽器
         */
        fun openBrowser(url: String)

        fun finish()
    }

    interface IWelcomeModel {
        fun loadData(present: IWelcomePresent, cb: (HttpResponse<WelcomeBean>) -> Unit)

        fun login(username: String, password: String, cb: (HttpResponse<UserBean>) -> Unit)
    }
}