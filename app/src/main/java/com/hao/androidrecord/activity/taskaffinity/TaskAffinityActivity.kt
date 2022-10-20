package com.hao.androidrecord.activity.taskaffinity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 单独设置android:taskAffinity不会产生任何作用。
 * 设置Activity启动模式为singleTask或者设置FLAG_ACTIVITY_NEW_TASK，再来看看任务栈的情况，代码就不贴出来了，
 *
 *
 * 首先我们来聊聊allowTaskReparenting属性，它的主要作用是activity的迁移，即从一个task迁移到另一个task，这个迁移跟activity的taskAffinity有关。
 * 当allowTaskReparenting的值为“true”时，则表示Activity能从启动的Task移动到有着affinity的Task（当这个Task进入到前台时），
 * 当allowTaskReparenting的值为“false”，表示它必须呆在启动时呆在的那个Task里。如果这个特性没有被设定，
 * 元素(当然也可以作用在每次activity元素上)上的allowTaskReparenting属性的值会应用到Activity上。默认值为“false”。
 * 这样说可能还比较难理解，我们举个例子，比如现在有两个应用A和B，A启动了B的一个ActivityC，然后按Home键回到桌面，再单击B应用时，
 * 如果此时，allowTaskReparenting的值为“true”，那么这个时候并不会启动B的主Activity，而是直接显示已被应用A启动的ActivityC，
 * 我们也可以认为ActivityC从A的任务栈转移到了B的任务栈中。这就好比我们在路边收养了一只与主人走失了的猫，养着养着突然有一天，主人找上门来了，这只猫也就被带回去了

作者：HarveyLegend
链接：https://www.jianshu.com/p/e8d3cba362a9
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class TaskAffinityActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}