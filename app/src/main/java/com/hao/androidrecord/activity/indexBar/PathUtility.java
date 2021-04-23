/**
 *
 */
package com.hao.androidrecord.activity.indexBar;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.hao.androidrecord.application.MyApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**用于管理本应用的路径
 * @ClassName: PathUtility
 * @Description:
 * @author WangJun
 * @date 2012-5-6 上午11:30:47
 */
public class PathUtility {

    private final static String DBPATH = "/aoyouji/breadtrip/";
    public final static String DB_CITY_PATH = PathUtility.getDBPath().getPath()+"/city";
    public final static String DBCITYNAME = "country_province_city.db";
    public final static String DB_CITY_FILE_PATH = DB_CITY_PATH+File.separator+DBCITYNAME;



    /**
     * 获取DB的存储路径
     * @author WangJun
     * @since 2012-7-18
     * @return
     */
    public final static File getDBPath() {
        File file = new File(MyApplication.Companion.getInstance().getExternalFilesDir(null), DBPATH);
        if(!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    /**
     * 拷贝选择地区的数据到sdcard
     */
    public  static void copyCityDataToSdcard(Context context){
        File file = new File(DB_CITY_FILE_PATH);
        InputStream is = null;
        FileOutputStream fos = null;
        if(!file.exists()){
            File path = new File(DB_CITY_PATH);
            path.mkdir();
            try {
                is = context.getAssets().open(DBCITYNAME);
                fos = new FileOutputStream(file);
                byte[] buffer=new byte[1024];
                int count = 0;
                while((count = is.read(buffer))>0){
                    fos.write(buffer, 0, count);
                }
                fos.flush();
                fos.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
                file.delete();
            }finally{
                try {
                    if(fos!=null){
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if(is!=null){
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }


    }
}
