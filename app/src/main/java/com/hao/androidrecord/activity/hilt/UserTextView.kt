package com.hao.androidrecord.activity.hilt

import android.content.Context
import android.util.AttributeSet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserTextView: androidx.appcompat.widget.AppCompatTextView {

    @Inject
    lateinit var user:User

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        text = "${user.name}的mood是${user.mood}"
    }
}