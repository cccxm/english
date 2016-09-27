package com.cccxm.english.mvp.model

import android.content.Context
import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.preference.PreferenceManager
import com.cccxm.english.R

/**
 * 手势管理类
 * 陈小默 16/9/26.
 */
class GestureModel(val context: Context) {

    val GESTURE_LIB_NAME = "gesture_lib"
    val GESTURE_TEMP_NAME = "GESTURE_TEMP"
    val GESTURE_SAFE_NAME = "GESTURE_SAFE"
    val gestureLib: GestureLibrary = GestureLibraries.fromPrivateFile(context, GESTURE_LIB_NAME)

    init {
        gestureLib.load()
    }

    val switch: Boolean get() {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val key = context.resources.getString(R.string.pref_key_safe_switch)
        return sp.getBoolean(key, false)
    }
    val level: Int get() {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val key = context.resources.getString(R.string.pref_key_safe_level)
        return sp.getString(key, "2").toInt()
    }

    fun temp(gesture: Gesture): Boolean {
        gestureLib.addGesture(GESTURE_TEMP_NAME, gesture)
        return gestureLib.save()
    }

    fun recognizeTemp(gesture: Gesture): Boolean {
        gestureLib.recognize(gesture).filter { prediction ->
            prediction.name == GESTURE_TEMP_NAME
        }.forEach { prediction ->
            return prediction.score > 12
        }
        return false
    }

    fun recognizeSafe(gesture: Gesture): Boolean {
        gestureLib.recognize(gesture).filter { prediction ->
            prediction.name == GESTURE_SAFE_NAME
        }.forEach { prediction ->
            return prediction.score > level
        }
        return false
    }

    fun save(gesture: Gesture): Boolean {
        gestureLib.addGesture(GESTURE_SAFE_NAME, gesture)
        return gestureLib.save()
    }
}