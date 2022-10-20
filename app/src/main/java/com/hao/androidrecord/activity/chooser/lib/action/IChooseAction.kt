package com.hao.androidrecord.activity.chooser.lib.action

import com.hao.androidrecord.activity.chooser.lib.ChooserView
import com.hao.androidrecord.activity.chooser.lib.IGroupManagerContext
import com.hao.androidrecord.activity.chooser.lib.OnChooseChangeListener


/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: cqbbyzh@gmial.com or 1037914505@qq.com
 * Time: 2020/3/7
 * Version: 1.0
 * Description:
 * ===================================================================
 */
interface IChooseAction {
    fun action(
        chooserView: ChooserView,
        chooserViewGroupManager: IGroupManagerContext,
        chooseChangeListener: OnChooseChangeListener?
    )
}
