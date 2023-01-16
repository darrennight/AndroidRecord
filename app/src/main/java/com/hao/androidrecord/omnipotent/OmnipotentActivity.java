package com.hao.androidrecord.omnipotent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;

//https://github.com/linhaojian/Omnipotent
public class OmnipotentActivity extends AppCompatActivity {
    private Button btn1,btn2;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omnipotent);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        frameLayout = findViewById(R.id.framelayout);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示Fragment
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout,new FragmentA()).commit();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //发送数据
                        OmnipotentManager.getInstance().invokeInterface("btn2","我是线程");
                    }
                }.start();
            }
        });
        initOmnipotent();
    }

    /**
     * 添加接口
     */
    private void initOmnipotent() {
        //添加一个有参有返回接口函数
        OmnipotentManager.getInstance().addInterface("btn1", new OmnipotentInterfaceHasParamHasResult<Integer,String>() {
            @Override
            public String function(Integer integer) {
                return "接收fragment发送的数据："+integer;
            }
        }).addInterface("btn2", new OmnipotentInterfaceHasParamNoResult<String>() {
            @Override
            public void function(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OmnipotentActivity.this,"接收线程发送的数据："+s,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
