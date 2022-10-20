package cn.bpking.app.helper

import cn.bpking.app.BuildConfig
import com.airbnb.deeplinkdispatch.DeepLinkSpec

@DeepLinkSpec(prefix = [BuildConfig.SCHEME_NAME + "://"])
@Retention(AnnotationRetention.BINARY)
annotation class AppDeepLink(vararg val value: String)