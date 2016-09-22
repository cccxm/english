package com.cccxm.english.config

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/8/29.
 */
class Urls {
    companion object {
        var networkState = NetState.DISCONNECTION
        const val HOST = "http://192.168.1.112/smart/"
        const val WELCOME_ADVERTISEMENT = "welcomeAd"
        const val LOGIN = "login"
        const val REGISTER = "register"
        const val TONGUE_LIB_LIST = "user/lib/tongue/list"
        const val DOWNLOAD_LIB = "user/lib/download"
        const val SUBMIT = "user/score/submit"
    }

    enum class NetState {
        DISCONNECTION,
        WIFI,
        DATA_GPRS,
        DATA_3G,
        DATA_4G
    }
}