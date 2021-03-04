package com.hao.androidrecord.activity.hilt

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

//hilt 注解初始化实例不能直接处理默认值 需要第二个构造器形式

//ActivityScoped 在activity范围内数据共享
@ActivityScoped
data class User(var name:String,var mood:String){
    @Inject constructor():this("111","qqq")
}