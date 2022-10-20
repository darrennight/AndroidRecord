package com.hao.androidrecord.activity

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.gzuliyujiang.oaid.DeviceID
import com.github.gzuliyujiang.oaid.DeviceIdentifier
import com.github.gzuliyujiang.oaid.IGetter
import com.hao.androidrecord.R


class OAIDActivity:AppCompatActivity() {

    private var tvDeviceIdResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oaid)

        val tvDeviceInfo = findViewById<TextView>(R.id.tv_device_info)
        tvDeviceInfo.setText(obtainDeviceInfo())
        tvDeviceIdResult = findViewById(R.id.tv_device_id_result)


        findViewById<Button>(R.id.btn_get_device_id_1).setOnClickListener {
            obtainDeviceId()
        }

        findViewById<Button>(R.id.btn_get_device_id_2).setOnClickListener {
            tvDeviceIdResult?.setText(String.format("ClientId: %s", DeviceIdentifier.getClientId()));
        }
    }


    private fun obtainDeviceId() {
        val builder = StringBuilder()
        builder.append("IMEI: ")
        // 获取设备唯一标识，只支持Android 10之前的系统，需要READ_PHONE_STATE权限，可能为空
        val imei = DeviceIdentifier.getIMEI(this)
        if (TextUtils.isEmpty(imei)) {
            builder.append("DID/IMEI/MEID获取失败")
        } else {
            builder.append(imei)
        }
        builder.append("\n")
        builder.append("AndroidID: ")
        // 获取安卓ID，可能为空
        val androidID = DeviceIdentifier.getAndroidID(this)
        if (TextUtils.isEmpty(androidID)) {
            builder.append("AndroidID获取失败")
        } else {
            builder.append(androidID)
        }
        builder.append("\n")
        builder.append("WidevineID: ")
        // 获取数字版权管理ID，可能为空
        val widevineID = DeviceIdentifier.getWidevineID()
        if (TextUtils.isEmpty(widevineID)) {
            builder.append("WidevineID获取失败")
        } else {
            builder.append(widevineID)
        }
        builder.append("\n")
        builder.append("PseudoID: ")
        // 获取伪造ID，根据硬件信息生成，不会为空，有大概率会重复
        builder.append(DeviceIdentifier.getPseudoID())
        builder.append("\n")
        builder.append("GUID: ")
        // 获取GUID，随机生成，不会为空
        builder.append(DeviceIdentifier.getGUID(this))
        builder.append("\n")
        // 是否支持OAID/AAID
        builder.append("supported: ").append(DeviceID.supportedOAID(this))
        builder.append("\n")
        builder.append("OAID: ")
        // 获取OAID，同步调用，第一次可能为空
        builder.append(DeviceIdentifier.getOAID(this))
        builder.append("\n")
        // 获取OAID/AAID，异步回调
        DeviceID.getOAID(this, object : IGetter {
            override fun onOAIDGetComplete(result: String) {
                // 不同厂商的OAID/AAID格式是不一样的，可进行MD5、SHA1之类的哈希运算统一
                builder.append("OAID/AAID: ").append(result)
                tvDeviceIdResult?.setText(builder)
            }

            override fun onOAIDGetError(error: Exception) {
                // 获取OAID/AAID失败
                builder.append("OAID/AAID: ").append(error)
                tvDeviceIdResult?.setText(builder)
            }
        })
    }

    private fun obtainDeviceInfo(): String? {
        val sb = StringBuilder()
        sb.append("BrandModel：")
        sb.append(Build.BRAND)
        sb.append(" ")
        sb.append(Build.MODEL)
        sb.append("\n")
        sb.append("Manufacturer：")
        sb.append(Build.MANUFACTURER)
        sb.append("\n")
        sb.append("SystemVersion：")
        sb.append(Build.VERSION.RELEASE)
        sb.append(" (Level ")
        sb.append(Build.VERSION.SDK_INT)
        sb.append(")")
        return sb.toString()
    }

}