package com.cccxm.master

import android.content.Context
import com.cccxm.dao.DaoMaster
import org.greenrobot.greendao.database.Database

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/1.
 */
object DBMaster {
    private var master: DaoMaster? = null
    fun init(context: Context, dbName: String) {
        if (master == null) {
            synchronized(DBMaster, {
                if (master == null) {
                    var help = object : DaoMaster.OpenHelper(context, dbName, null) {
                        override fun onUpgrade(db: Database, oldVersion: Int, newVersion: Int) {
                            upgrade(db, oldVersion, newVersion)
                        }
                    }
                    var db = help.writableDb
                    master = DaoMaster(db)
                }
            })
        }
    }

    fun getMaster(): DaoMaster = master!!

    fun newSession() = getMaster().newSession()

    private fun upgrade(db: Database, oldVersion: Int, newVersion: Int) {

    }
}