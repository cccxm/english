package com.cxm.utils

import android.content.Context
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

/**
 * Bean類的相關操作
 * 陈小默 16/8/19.
 */
class BeanUtils {

    /**
     * 序列化到本地
     */
    fun serialized(context: Context, serializable: Serializable, fileName: String? = null) {
        val name = fileName ?: serializable.javaClass.name
        val output = ObjectOutputStream(context.openFileOutput(name, Context.MODE_PRIVATE))
        output.writeObject(serializable)
        output.flush()
        output.close()
    }

    /**
     * 從本地文件反序列化
     */
    fun <S : Serializable> deSerialized(context: Context, clazz: Class<S>, fileName: String? = null): S? = try {
        val input = ObjectInputStream(context.openFileInput(fileName ?: clazz.name))
        val s: Serializable? = input.readObject() as Serializable
        input.close()
        s as S
    } catch (e: Exception) {
        null
    }

}