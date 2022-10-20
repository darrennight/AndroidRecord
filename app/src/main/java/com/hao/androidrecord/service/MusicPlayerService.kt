package com.hao.androidrecord.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import com.hao.androidrecord.R

/**
 * 后台运行的Service系统优先级相对较低，当系统内存不足时，在后台运行的Service就有可能被回收，为了保持后台服务的正常运行及相关操作，可以选择将需要保持运行的Service设置为前台服务，从而使APP长时间处于后台或者关闭（进程未被清理）时，服务能够保持工作。
 * 如何保证 Service 不被杀死
在 Service 的 onStartCommand 中返回 START_STICKY，该标志使得 Service 被杀死后尝试再次启动 Service
提高 Service 优先级，比如设置成前台服务
 */
class MusicPlayerService:Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("=====service","onStartCommandonStartCommand")
        startForeground()
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 启动前台服务
     */
    private fun startForeground() {
        var channelId: String?
        // 8.0 以上需要特殊处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelId = createNotificationChannel("kim.hsl", "ForegroundService")
        } else {
            channelId = ""
        }
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, channelId?:"")
        val notification: Notification = builder.setOngoing(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(1, notification)
    }

    /**
     * 创建通知通道
     * @param channelId
     * @param channelName
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String? {
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("=====service","MusicPlayerServiceMusicPlayerService")
        stopForeground(true)


    }
}