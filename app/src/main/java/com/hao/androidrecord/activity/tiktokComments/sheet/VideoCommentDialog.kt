package com.hao.androidrecord.activity.tiktokComments.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.tiktokComments.CommentMoreBean
import com.hao.androidrecord.activity.tiktokComments.FirstLevelBean
import com.hao.androidrecord.activity.tiktokComments.SecondLevelBean
import kotlinx.android.synthetic.main.dialog_comments.*

class VideoCommentDialog:BaseBottomSheetDialog() {


    companion object{
        fun getInstance():VideoCommentDialog{
            return VideoCommentDialog()
        }
    }

    private val data:ArrayList<MultiItemEntity> by lazy {
        ArrayList()
    }
    private val datas:ArrayList<FirstLevelBean> by lazy {
        ArrayList()
    }
    private val content = "我听见你的声音，有种特别的感觉。让我不断想，不敢再忘记你。如果真的有一天，爱情理想会实现，我会加倍努力好好对你，永远不改变"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_comments, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        dataSort(0)
        val manager = LinearLayoutManager(requireContext())
        manager.orientation = LinearLayoutManager.VERTICAL
        tv_video_comments.layoutManager = manager

        val adapter = VideoCommentsAdapter(requireContext(), data)
        adapter?.listener = object : VideoCommentsAdapter.AddMoreComments {
            override fun addMore(moreBean: CommentMoreBean, position: Int) {

                val secondLevelBean = SecondLevelBean()
                secondLevelBean.content = "more comment" + 1
                secondLevelBean.createTime = System.currentTimeMillis()
                secondLevelBean.headImg =
                    "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1918451189,3095768332&fm=26&gp=0.jpg"
                secondLevelBean.id = 1.toString() + ""
                secondLevelBean.isLike = 0
                secondLevelBean.likeCount = 0
                secondLevelBean.userName = "星梦缘" + 1
                secondLevelBean.isReply = 0
                secondLevelBean.replyUserName = "闭嘴家族" + 1
                secondLevelBean.totalCount = moreBean.getTotalCount() + 1

                datas[moreBean.position.toInt()].secondLevelBeans.add(secondLevelBean)
                dataSort(0)
                adapter?.notifyDataSetChanged()
            }
        }
        tv_video_comments.adapter = adapter

    }



    //原始数据 一般是从服务器接口请求过来的
    private fun initData() {
        val size = 10
        for (i in 0 until size) {
            val firstLevelBean = FirstLevelBean()
            firstLevelBean.content =
                "第" + (i + 1) + "人评论内容" + if (i % 3 == 0) content + (i + 1) + "次" else ""
            firstLevelBean.createTime = System.currentTimeMillis()
            firstLevelBean.headImg =
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3370302115,85956606&fm=26&gp=0.jpg"
            firstLevelBean.id = i.toString() + ""
            firstLevelBean.isLike = 0
            firstLevelBean.likeCount = i.toLong()
            firstLevelBean.userName = "星梦缘" + (i + 1)
            firstLevelBean.totalCount = (i + size).toLong()
            val beans: MutableList<SecondLevelBean> = ArrayList()
            for (j in 0..9) {
                val secondLevelBean = SecondLevelBean()
                secondLevelBean.content =
                    "一级第" + (i + 1) + "人 二级第" + (j + 1) + "人评论内容" + if (j % 3 == 0) content + (j + 1) + "次" else ""
                secondLevelBean.createTime = System.currentTimeMillis()
                secondLevelBean.headImg =
                    "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1918451189,3095768332&fm=26&gp=0.jpg"
                secondLevelBean.id = j.toString() + ""
                secondLevelBean.isLike = 0
                secondLevelBean.likeCount = j.toLong()
                secondLevelBean.userName = "星梦缘" + (i + 1) + "  " + (j + 1)
                secondLevelBean.isReply = if (j % 5 == 0) 1 else 0
                secondLevelBean.replyUserName = if (j % 5 == 0) "闭嘴家族$j" else ""
                secondLevelBean.totalCount = firstLevelBean.totalCount
                beans.add(secondLevelBean)
                firstLevelBean.secondLevelBeans = beans
            }
            datas.add(firstLevelBean)
        }
    }

    /**
     * 对数据重新进行排列
     * 目的是为了让一级评论和二级评论同为item
     * 解决滑动卡顿问题
     *
     * @param position
     */
    private fun dataSort(position: Int) {

        if (position <= 0) data.clear()
        var posCount = data.size
        val count = datas.size
        for (i in 0 until count) {
            if (i < position) continue

            //一级评论
            val firstLevelBean = datas[i] ?: continue
            firstLevelBean.position = i
            posCount += 2
            val secondLevelBeans = firstLevelBean.secondLevelBeans
            if (secondLevelBeans == null || secondLevelBeans.isEmpty()) {
                firstLevelBean.positionCount = posCount
                data.add(firstLevelBean)
                continue
            }
            val beanSize = secondLevelBeans.size
            posCount += beanSize
            firstLevelBean.positionCount = posCount
            data.add(firstLevelBean)

            //二级评论
            for (j in 0 until beanSize) {
                val secondLevelBean = secondLevelBeans[j]
                secondLevelBean.childPosition = j
                secondLevelBean.position = i
                secondLevelBean.positionCount = posCount
                data.add(secondLevelBean)
            }

            //展示更多的item
            if (beanSize <= 18) {
                val moreBean = CommentMoreBean()
                moreBean.position = i.toLong()
                moreBean.positionCount = posCount.toLong()
                moreBean.totalCount = firstLevelBean.totalCount
                data.add(moreBean)
            }
        }
    }
    override fun getHeight(): Int {
        return resources.displayMetrics.heightPixels - 600
    }
}