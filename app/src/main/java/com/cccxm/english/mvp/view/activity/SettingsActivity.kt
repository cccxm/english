package com.cccxm.english.mvp.view.activity

import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.provider.Settings
import com.cccxm.english.R
import com.cccxm.english.config.NetState
import com.cccxm.english.config.UserHolder
import com.cxm.mvp.BaseActivity
import com.cxm.mvp.IPresenter
import com.cxm.utils.ActivityUtils
import com.cxm.utils.NetUtils
import com.cxm.utils.NetworkType
import com.cxm.utils.StringUtils

class SettingsActivity : BaseActivity<IPresenter>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val settingsFragment = SettingsFragment()
        fragmentManager.beginTransaction()
                .replace(R.id.settingsFrameLayout, settingsFragment)
                .commit()
    }

    class SettingsFragment : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_settings)
        }

        fun register() {
            val userVipPref = findPreference(R.string.pref_key_user_vip)
            userVipPref.setOnPreferenceChangeListener { pref, value ->
                flushUserVip(value)
                true
            }
            val userLogoutPref = findPreference(R.string.pref_key_user_logout)
            userLogoutPref.setOnPreferenceClickListener {
                UserHolder.deleteUser(activity)
                ActivityUtils.finishActivities { startActivity(Intent(activity, LoginActivity::class.java)) }
                true
            }
            val netOfflinePref = findPreference(R.string.pref_key_net_offline)
            netOfflinePref.setOnPreferenceChangeListener { pref, any ->
                flushFlowSet(isOffline = any as Boolean)
                findPreference(R.string.pref_key_notify_settings).isEnabled = !any
                true
            }
            val netFlowMsPref = findPreference(R.string.pref_key_net_flow_change)
            netFlowMsPref.setOnPreferenceChangeListener { pref, values ->
                flushFlowSet(pref, values as Set<String>)
                true
            }
            val notifySwitchPref = findPreference(R.string.pref_key_notify_switch)
            notifySwitchPref.setOnPreferenceChangeListener { pref, any ->
                flushNotifySwitch(pref, any as Boolean)
                flushNotifyRingtone(allowNotify = any)
                true
            }
            val notifyRingPref = findPreference(R.string.pref_key_notify_ring)
            notifyRingPref.setOnPreferenceChangeListener { pref, any ->
                flushNotifyRingtone(pref, any as String)
                true
            }
        }

        fun flush() {
            flushUserInfo()
            flushUserVip()
            flushFlowSet()
            flushNetState()
            flushNotifySwitch()
            flushNotifyRingtone()
        }

        /**
         * 刷新用户信息
         */
        private fun flushUserInfo() {
            val pref = findPreference(R.string.pref_key_user_info)
            val user = UserHolder.getUser()
            if (user == null) {
                findPreference(R.string.pref_key_user_logout).isEnabled = false
                if (NetState.state.value > 0) {
                    pref.title = "未登录"
                    pref.summary = "点击登录"
                    pref.intent = Intent(activity, LoginActivity::class.java)
                } else {
                    pref.title = "网络连接失败"
                    pref.summary = "点击打开网络连接"
                    pref.intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                }
            } else {
                findPreference(R.string.pref_key_user_logout).isEnabled = true
                pref.title = "您好 陈小默"
                pref.summary = "您当前的积分:${user.score}"
                pref.intent = null
            }
        }

        /**
         * 刷新用户会员状态
         */
        private fun flushUserVip(value: Any? = null) {
            val userVipEditPref = findPreference(R.string.pref_key_user_vip) as EditTextPreference
            val user = UserHolder.getUser()
            if (user == null) {
                userVipEditPref.summary = getString(R.string.pref_summary_user_vip)
            } else {
                if (value != null) {
                    if (value.toString().toInt() > 0) {
                        userVipEditPref.summary = "您的会员时长为 $value 个月"
                    } else userVipEditPref.summary = getString(R.string.pref_summary_user_vip)
                } else {
                    val v = userVipEditPref.sharedPreferences.getString(userVipEditPref.key, "0").toInt()
                    if (v > 0) {
                        userVipEditPref.summary = "您的会员时长为 $v 个月"
                    } else userVipEditPref.summary = getString(R.string.pref_summary_user_vip)
                }
            }
        }

        /**
         * 更新省流量视图
         */
        private fun flushFlowSet(preference: Preference? = null, values: Set<String>? = null, isOffline: Boolean? = null) {
            val pref = preference ?: findPreference(R.string.pref_key_net_flow_change)
            if (isOffline ?: getBoolean(R.string.pref_key_net_offline)) {
                pref.summary = "当前为离线模式"
            } else {
                val set = values ?: getStringSet(R.string.pref_key_net_flow_change)
                val builder = StringBuilder()
                builder.append("允许WiFi")
                set?.forEach { action -> builder.append(",").append(action) }
                builder.append("下联网")
                pref.summary = builder.toString()
            }
        }

        /**
         * 更新网络信息
         */
        private fun flushNetState() {
            val pref = findPreference(R.string.pref_key_net_flow_info)
            val state = NetState.state
            if (state.value > 0) {
                pref.title = "当前使用的是${NetState.state.name}网络"
                if (NetState.state == NetworkType.WIFI) {
                    pref.summary = "WiFi信号强度${NetUtils(activity).WIFI_RSSI}dBm"
                }
            } else pref.title = "网络未连接"
        }

        /**
         * 刷新推送说明
         */
        private fun flushNotifySwitch(preference: Preference? = null, value: Boolean? = null) {
            val pref = preference ?: findPreference(R.string.pref_key_notify_switch)
            pref.summary = if (value ?: getBoolean(R.string.pref_key_notify_switch))
                "当前允许推送消息"
            else "当前禁止推送消息"
        }

        /**
         * 刷新通知铃声状态
         */
        private fun flushNotifyRingtone(preference: Preference? = null, content: String? = null, allowNotify: Boolean? = null) {
            val pref = preference ?: findPreference(R.string.pref_key_notify_ring)
            pref.isEnabled = (allowNotify ?: getBoolean(R.string.pref_key_notify_switch))
            val uri = Uri.parse(content ?: get(R.string.pref_key_notify_ring))
            val ring = RingtoneManager.getRingtone(activity, uri)
            val name = ring.getTitle(activity)
            pref.summary = if (StringUtils.isBlank(name)) "无" else name
        }


        private fun findPreference(resId: Int): Preference {
            val key = getString(resId)
            return findPreference(key)
        }

        private fun getBoolean(resId: Int, defaultValue: Boolean = false): Boolean {
            val key = getString(resId)
            return preferenceManager.sharedPreferences.getBoolean(key, defaultValue)
        }

        private fun getStringSet(resId: Int, defaultValue: Set<String>? = null): Set<String>? {
            val key = getString(resId)
            return preferenceManager.sharedPreferences.getStringSet(key, defaultValue)
        }

        private fun get(keyId: Int, defaultValue: String? = null): String {
            val key = getString(keyId)
            return preferenceManager.sharedPreferences.getString(key, defaultValue)
        }

        var netTag = ""

        override fun onStart() {
            super.onStart()
            register()
            netTag = NetState.subscribe {
                flush()
            }
        }

        override fun onStop() {
            NetState.unSubscribe(netTag)
            super.onStop()
        }
    }
}