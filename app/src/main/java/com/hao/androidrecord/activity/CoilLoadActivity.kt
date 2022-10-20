package com.hao.androidrecord.activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.Coil
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.decode.VideoFrameDecoder
import coil.fetch.VideoFrameFileFetcher
import coil.fetch.VideoFrameUriFetcher
import coil.load
import coil.request.CachePolicy
import coil.request.videoFrameMillis
import coil.size.Scale
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.hao.androidrecord.R
import com.hao.androidrecord.util.ViewUtils
import com.hao.androidrecord.util.loadImage
import kotlinx.android.synthetic.main.activity_coil_load.*
import java.io.File

class CoilLoadActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coil_load)
//        val url = "https://img-blog.csdnimg.cn/202101240.png"
        val url = "https://img-blog.csdnimg.cn/20210124002108308.png"


        iv_img.load(url){
            //加载时 渐显
            crossfade(true)
            crossfade(500)
            //
            placeholder(R.drawable.e19)
            error(R.drawable.e)
        }

        //自定义拓展函数--配置了通用的加载配置
        //iv_img.loadImage(url)



        //全局配置 通用参数
        /*Coil.setImageLoader(
            ImageLoader.Builder(application)
                .placeholder(R.drawable.e19) //占位符
                .error(R.drawable.e) //错误图
                //.memoryCachePolicy(CachePolicy.ENABLED) //开启内存缓存
                //.callFactory(createOkHttp(application)) //主动构造 OkHttpClient 实例
                .build()
        )*/


        iv_img2.load(url){
            //高斯模糊
//            transformations(BlurTransformation(context = applicationContext, radius = 5f, sampling = 5f))
            //圆形
//            transformations(CircleCropTransformation())
            //灰色
//            transformations(GrayscaleTransformation())
            //圆角
            transformations(RoundedCornersTransformation(topRight = 10f, topLeft = 10f, bottomLeft =  10f, bottomRight =  10f))
        }

        iv_img3.load(url){
            //加载时 渐显
            crossfade(true)
            crossfade(500)
            scale(Scale.FIT)
            size(ViewUtils.dp2px(100f),ViewUtils.dp2px(100f))

        }

        //加载gif
        val imageLoader = ImageLoader.Builder(this)
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(ImageDecoderDecoder(this@CoilLoadActivity))
                } else {
                    add(GifDecoder())
                }
            }
            .build()
        //iv_img3.load(url,imageLoader)

        //svg
        val imageLoaderSVG = ImageLoader.Builder(this)
            .componentRegistry {
                add(SvgDecoder(this@CoilLoadActivity))
            }
            .build()

        //video
        val imageLoaderVideo = ImageLoader.Builder(this)
            .componentRegistry {
                add(VideoFrameFileFetcher(this@CoilLoadActivity))
                add(VideoFrameUriFetcher(this@CoilLoadActivity))
                add(VideoFrameDecoder(this@CoilLoadActivity))
            }
            .build()
//        iv_img3.load(File("/path/to/video.mp4")) {
//            videoFrameMillis(1000)
//        }

    }
}