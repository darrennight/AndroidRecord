package com.hao.androidrecord.activity.chooser.lib

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: cqbbyzh@gmial.com or 1037914505@qq.com
 * Time: 2020/3/7
 * Version: 1.0
 * Description:
 * ===================================================================
 */
interface IGroupManagerContext {
    fun getMaxSelectedCountByGroupTag(groupTag: String): Int
}
