package com.example.a86136.grlc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mydenglu;
    private Button myzhuce;
    private EditText myzhangh;
    MyedittextActivity mymima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myfind();
    }
    public void myfind(){
        mydenglu = (Button)findViewById(R.id.denglu);
        myzhuce = (Button)findViewById(R.id.zhuce);
        myzhangh = (EditText) findViewById(R.id.zhangh);
        mymima = (MyedittextActivity) findViewById(R.id.mima);
        mydenglu.setOnClickListener(MainActivity.this);
        myzhuce.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.denglu:
                if(myzhangh.getText().toString().equals("")){
                    Toast.makeText(this,"请输入账号!",Toast.LENGTH_SHORT).show();
                } else if (mymima.getText().toString().equals("")){
                    Toast.makeText(this,"请输入密码!",Toast.LENGTH_SHORT).show();
                } else if (getUser(myzhangh.getText().toString(),mymima.getText().toString()+".cn")){
                    Toast.makeText(this,"登录失败!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"登录成功!",Toast.LENGTH_SHORT).show();
                    send();
                }
                break;
            case R.id.zhuce:
                Intent intent2 = new Intent(MainActivity.this,ZcActivity.class);
                startActivity(intent2);
                break;
        }
    }
    //判断登录是否成功
    public boolean getUser(String name,String pwd) {
        boolean tt=true;
        SharedPreferences sp = getSharedPreferences("User",Context.MODE_PRIVATE);
        String m = sp.getString(name,"");
        if(m.equals(pwd)){
            tt=false;
        }
        return tt;
    }
    //传递账号密码
    public void send(){
        Intent intent1 = new Intent(MainActivity.this,ZhujmActivity.class);
        intent1.putExtra("zhangh",myzhangh.getText().toString().trim());
        intent1.putExtra("mima",mymima.getText().toString().trim());
        startActivity(intent1);
    }
}
