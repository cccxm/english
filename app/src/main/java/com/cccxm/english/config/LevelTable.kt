package com.cccxm.english.config

/**
 * 菩提本无树
 * 明镜亦非台
 * 本来无一物
 * 何处惹尘埃
 * 陈小默 16/9/9.
 */
object LevelTable {
    private val table = longArrayOf(43200000L,
            86400000L,
            129600000L,
            216000000L,
            345600000L,
            561600000L,
            907200000L,
            1468800000L,
            2376000000L,
            3844800000L)

    fun submit(level: Int): Boolean = level >= 5

    fun next(level: Int): Long = table[level]

    fun over(level: Int): Boolean = level == table.size - 1
}