package com.hao.androidrecord.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.HandlerAction
import com.hao.androidrecord.custom.KeyboardAction
import com.hao.androidrecord.custom.KeyboardWatcher
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity:AppCompatActivity(), KeyboardWatcher.SoftKeyboardStateListener, HandlerAction,
    KeyboardAction {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        iv_login_wechat.setOnClickListener {  }

        postDelayed({
            KeyboardWatcher.with(this@LoginActivity)
                .setListener(this@LoginActivity)
        }, 500)
    }
    /** logo 缩放比例 */
    private val logoScale: Float = 0.8f

    /** 动画时间 */
    private val animTime: Int = 300
    override fun onSoftKeyboardOpened(keyboardHeight: Int) {
        // 执行位移动画
        ll_login_body?.let {
            val objectAnimator: ObjectAnimator = ObjectAnimator.ofFloat(it,
                "translationY", 0f, (-(btn_login_commit?.height?.toFloat() ?: 0f)))
            objectAnimator.duration = animTime.toLong()
            objectAnimator.interpolator = AccelerateDecelerateInterpolator()
            objectAnimator.start()
        }

        // 执行缩小动画
        iv_login_logo?.let {
            it.pivotX = it.width / 2f
            it.pivotY = it.height.toFloat()
            val animatorSet = AnimatorSet()
            val scaleX = ObjectAnimator.ofFloat(it, "scaleX", 1f, logoScale)
            val scaleY = ObjectAnimator.ofFloat(it, "scaleY", 1f, logoScale)
            val translationY = ObjectAnimator.ofFloat(it, "translationY",
                0f, (-(btn_login_commit?.height?.toFloat() ?: 0f)))
            animatorSet.play(translationY).with(scaleX).with(scaleY)
            animatorSet.duration = animTime.toLong()
            animatorSet.start()
        }


    }

    override fun onSoftKeyboardClosed() {
        // 执行位移动画
        ll_login_body?.let {
            val objectAnimator: ObjectAnimator = ObjectAnimator.ofFloat(it,
                "translationY", it.translationY, 0f)
            objectAnimator.duration = animTime.toLong()
            objectAnimator.interpolator = AccelerateDecelerateInterpolator()
            objectAnimator.start()
        }

        // 执行放大动画
        iv_login_logo?.let {
            it.pivotX = it.width / 2f
            it.pivotY = it.height.toFloat()

            if (it.translationY == 0f) {
                return
            }

            val animatorSet = AnimatorSet()
            val scaleX: ObjectAnimator = ObjectAnimator.ofFloat(it, "scaleX", logoScale, 1f)
            val scaleY: ObjectAnimator = ObjectAnimator.ofFloat(it, "scaleY", logoScale, 1f)
            val translationY: ObjectAnimator = ObjectAnimator.ofFloat(it,
                "translationY", it.translationY, 0f)
            animatorSet.play(translationY).with(scaleX).with(scaleY)
            animatorSet.duration = animTime.toLong()
            animatorSet.start()
        }
    }
}