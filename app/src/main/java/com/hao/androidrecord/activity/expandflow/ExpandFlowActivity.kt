package com.hao.androidrecord.activity.expandflow

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.flowRecycle.DataConfig
import com.hao.androidrecord.activity.flowRecycle.ShowItem
import com.hao.androidrecord.custom.flow.SpaceItemDecoration
import com.hao.androidrecord.util.ViewUtils.dp2px
import kotlinx.android.synthetic.main.activity_expand_flow.*


//1种 改变layout的高度 知道item的高度和间隔 设置显示两行就设置layout高度是两行，展开就是layout(recycleview)的高度就是包裹内容的warp_content

//2种是 flowmanager在计算的时候超过两行的就停止测算

class ExpandFlowActivity:AppCompatActivity() {

    private var collapse = true
    private val minH:Int = dp2px(100f)
    private var maxH:Int = 0
    private lateinit var manager:FlowLayoutExpandManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_flow)
        manager = FlowLayoutExpandManager()
        rv_hisitory.layoutManager = manager
        rv_hisitory.addItemDecoration(SpaceItemDecoration(dp2px(10f)))
        rv_hisitory.adapter = FlowAdapter(this,DataConfig.getItems() as  ArrayList)


        btn_collse.setOnClickListener {
            if(collapse){
                performAnim2()
                collapse = false
                //这个没有动画效果
//                val params = rv_hisitory.layoutParams
//                params.height = maxH
//                rv_hisitory.layoutParams = params
            }else{
                performAnim2()
                collapse = true
//                val params = rv_hisitory.layoutParams
//                params.height = minH
//                rv_hisitory.layoutParams = params
            }
        }


        /*activity根布局是relayout 回调用三次绘制 lineay frame一次*/

        //还可以回调行数 只有一行 params.height = warp_content 按钮不能点击
        manager.onTotalHeightListener = FlowLayoutExpandManager.CallbackTotalHeight {
            maxH = manager.totalHeight
            if(maxH <= minH){
                btn_collse.text = "stop"
            }

            Log.e("====manager","${manager.totalHeight}")
        }
    }

    private fun performAnim2() {
        //View是否显示的标志

        //属性动画对象
        val va: ValueAnimator
        va = if (collapse) {
            //显示view，高度从0变到height值
            ValueAnimator.ofInt(minH, maxH)
        } else {
            //隐藏view，高度从height变为0
            ValueAnimator.ofInt(maxH, minH)
        }
        va.addUpdateListener { valueAnimator -> //获取当前的height值
            val h = valueAnimator.getAnimatedValue() as Int
            //动态更新view的高度
            rv_hisitory.getLayoutParams().height = h
            rv_hisitory.requestLayout()
        }
        va.setDuration(100)
        va.setInterpolator(FastOutSlowInInterpolator());
        //开始动画
        va.start()
    }

    class FlowAdapter(private val context: Context, private val list: ArrayList<ShowItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return MyHolder(View.inflate(context, R.layout.flow_expand_item, null))
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val textView = (holder as MyHolder).text
            textView.setBackgroundDrawable(list[position].color)
            textView.text = list[position].des
            holder.itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    list[position].des,
                    Toast.LENGTH_SHORT
                ).show()
                list.removeAt(position)
                notifyDataSetChanged()
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        internal inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text: TextView

            init {
                text = itemView.findViewById<View>(R.id.flow_text) as TextView
            }
        }
    }
}