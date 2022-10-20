package com.hao.androidrecord.deeplink

import com.airbnb.deeplinkdispatch.DeepLinkSpec

/**
 * 自定义的注解
 * 为每一个deep link URI 添加一个 "record_link://"前缀。
 */
//@DeepLinkSpec(prefix = [BuildConfig.SCHEME_NAME + "://"])
//前缀不能有下划线
@DeepLinkSpec(prefix = ["recordlink://"])
@Retention(AnnotationRetention.BINARY)
annotation class AppDeepLink(vararg val value: String)