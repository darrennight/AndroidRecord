package com.hao.androidrecord.activity.tiktokComments.sheet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.tiktokComments.bean.CommentEntity
import com.hao.androidrecord.activity.tiktokComments.bean.CommentMoreBean
import com.hao.androidrecord.activity.tiktokComments.bean.FirstLevelBean
import com.hao.androidrecord.activity.tiktokComments.bean.SecondLevelBean
import kotlinx.android.synthetic.main.item_comment_child_new.view.*
import kotlinx.android.synthetic.main.item_comments_parent.view.*

class VideoCommentsAdapter(val context: Context, val list: ArrayList<MultiItemEntity>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var listener:AddMoreComments?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == 0){
            ParentCommentsHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_comments_parent,
                    parent,
                    false
                )
            )
        }else if (viewType == 1){
            ChildCommentsHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_comment_child_new,
                    parent,
                    false
                )
            )
        }else if (viewType == 2){
            MoreCommentsHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_comment_new_more,
                    parent,
                    false
                )
            )
        }else{
            MoreCommentsHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_comment_new_more,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ParentCommentsHolder){
            bindParent(holder, position)
        }else if (holder is ChildCommentsHolder){
            bindChild(holder, position)
        }else if (holder is MoreCommentsHolder){
            bindMore(holder, position)
        }

    }


    private fun bindParent(holder: ParentCommentsHolder, position: Int){
        val data = list[position] as FirstLevelBean
        holder.itemView.apply {
            tv_like_count.text = ("${data.likeCount}")
            tv_user_name.text = ("${data.userName}")
            tv_content.text = ("${data.content}")
        }
    }

    private fun bindChild(holder: ChildCommentsHolder, position: Int){
        val data = list[position] as SecondLevelBean
        holder.itemView.apply {
            tv_child_like_count.text = ("${data.likeCount}")
            tv_child_user_name.text = ("${data.userName}")
            tv_child_content.text = ("${data.content}")
        }
    }

    private fun bindMore(holder: MoreCommentsHolder, position: Int){
        val moreBean = list[position] as CommentMoreBean
        holder.itemView.apply {
            setOnClickListener {
                listener?.addMore(moreBean,position)
            }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        val data = list[position]
        if (data.itemType == CommentEntity.TYPE_COMMENT_PARENT){
            return 0
        }else if (data.itemType == CommentEntity.TYPE_COMMENT_CHILD){
            return 1
        }else if (data.itemType == CommentEntity.TYPE_COMMENT_MORE){
            return 2
        }else{
            return 3
        }
    }

    class ParentCommentsHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    class ChildCommentsHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    class MoreCommentsHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    interface AddMoreComments{
        fun addMore(bean: CommentMoreBean, position: Int)
    }
}