package com.hao.androidrecord.activity.recycleState.custom

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.item_rv_state_parent.view.*


class RecycleState01Adapter(val context: Context):RecyclerView.Adapter<RecycleState01Adapter.RecycleStateHolder>() {
    //刷新数据 清除信息，退出界面刷新信息
    private val saveStateMap: MutableMap<Int, Parcelable> = mutableMapOf()

    //create an instance of ViewPool
    private val viewPool = RecyclerView.RecycledViewPool()


   /* fun addAll(mList: List<DataRadioDramaTimeItem?>?) {
        super.addAll(mList)
        //刷新数据，清除直播甬道的状态信息
        saveStateMap.clear()
    }*/

    override fun onViewRecycled(holder: RecycleStateHolder) {
        super.onViewRecycled(holder)


        val manager: RecyclerView.LayoutManager = holder.itemView.rv_child_state.layoutManager ?: return
        val parcelable = manager.onSaveInstanceState()
        if (parcelable != null) {
            saveStateMap[holder.bindingAdapterPosition] = parcelable
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleStateHolder {
        return RecycleStateHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_state_parent,parent,false))
    }

    override fun onBindViewHolder(holder: RecycleStateHolder, position: Int) {
        //获取集合中存储的广播剧layoutManager状态
        val parcelable = saveStateMap[holder.bindingAdapterPosition]
        if (parcelable != null) {
            //恢复之前的状态
            holder.itemView.rv_child_state.layoutManager?.onRestoreInstanceState(parcelable)
        }else{
            holder.itemView.rv_child_state.layoutManager?.scrollToPosition(0)
        }

        holder.itemView.apply {
            tv_position_text.text = "$position"

        }
        holder.bind()

        holder.itemView.rv_child_state.run {
            //设置共享pool 共享的 View Pool
            setRecycledViewPool(viewPool)

        }


    }

    override fun getItemCount(): Int {
        return 20
    }

    class RecycleStateHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val layoutManager = LinearLayoutManager(
            itemView.context,
            LinearLayoutManager.HORIZONTAL, false
        )
        private val adapter = RecycleStateChildAdapter(itemView.context)

        init {
//            adapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
            itemView.rv_child_state.layoutManager = layoutManager
            itemView.rv_child_state.adapter = adapter
        }
        fun bind(){
            //预加载 设置预加载的数量
            //可以通过嵌套的 RecyclerView 的 LinearLayoutManager ，调用 setInitialPrefetechItemCount() 方法来预设可能会显示的可见数量。在垂直滑动的时候，
            // 外层 RecyclerView 会要求内层 RecyclerView 进行预绑定，但是内层 RecyclerView 并不知道应该预加载多少个 Item，直到该内层 RecyclerView 可见的时候，其他的非预加载 Item 才会被加载(默认情况下只有俩个 Item 会被预加载)，这样会导致性能问题。
            //
            //作者：无伤大雅的你呀
            //链接：https://juejin.cn/post/6934810205968400398
            //来源：稀土掘金
            //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
            layoutManager.initialPrefetchItemCount = 4
            adapter.addItemData(listOf(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20"
            ))
        }
    }
}