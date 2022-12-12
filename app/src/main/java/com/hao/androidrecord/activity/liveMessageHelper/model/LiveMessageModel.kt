package com.hao.androidrecord.activity.liveMessageHelper.model

import android.os.Parcelable
import android.text.SpannableStringBuilder
import android.util.Log
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
class LiveMessageModel : LiveMessageIntrinsicModel(), Parcelable {

    //Transient表示不参与序列化，json解析库也不解析该字段
    @Transient
    var spannableString : SpannableStringBuilder? = null

}
