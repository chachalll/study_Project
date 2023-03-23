package com.example.a86136.grlc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZcActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mybutton1;
    private Button mybutton2;
    private EditText myzhangh;
    MyedittextActivity mymima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc);
        myfind();
    }
    public void myfind(){
        mybutton1 = (Button)findViewById(R.id.button1);
        mybutton2 = (Button)findViewById(R.id.button2);
        myzhangh = (EditText) findViewById(R.id.zhangh);
        mymima = (MyedittextActivity) findViewById(R.id.mima);

        mybutton1.setOnClickListener(this);
        mybutton2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
                if(myzhangh.getText().toString().equals("")){
                    Toast.makeText(this,"请输入账号!",Toast.LENGTH_SHORT).show();
                } else if (mymima.getText().toString().equals("")){
                    Toast.makeText(this,"请输入密码!",Toast.LENGTH_SHORT).show();
                }else if(checkUser(myzhangh.getText().toString())){
                    Toast.makeText(this,"用户名已被注册!",Toast.LENGTH_SHORT).show();
                }
                else {
                    saveUser(myzhangh.getText().toString(),mymima.getText().toString()+".cn");
                    Toast.makeText(ZcActivity.this,
                            "注册成功！",
                            Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(ZcActivity.this,MainActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.button2:
                Intent intent2 = new Intent(ZcActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
        }
    }
    //注册不成功
    public boolean checkUser(String name) {
        boolean tt=true;
        SharedPreferences sp = getSharedPreferences("User",Context.MODE_PRIVATE);
        String m = sp.getString(name,"");
        if(m.equals("")){
            tt=false;
        }
        return tt;
    }
   //注册成功
    public boolean saveUser(String name, String pwd){
        SharedPreferences sp = getSharedPreferences("User",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name,pwd);
        editor.commit();
        return true;
    }
}
