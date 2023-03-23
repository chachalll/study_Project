package com.example.a86136.grlc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class XtszActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView myzhangh;
    private TextView myname;
    private TextView mymima;
    private EditText myxmima;
    private Button mybutton1;
    private  Button mybutton2;
    String name;
    String mima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xtsz);
       myfind();
       recive();
    }
    public void myfind(){
         myzhangh = (TextView)findViewById(R.id.zhangh);
         myname = (TextView)findViewById(R.id.name);
         mymima = (TextView)findViewById(R.id.mima);
         myxmima = (EditText)findViewById(R.id.xmima);
        mybutton1 = (Button)findViewById(R.id.button1);
        mybutton2 = (Button)findViewById(R.id.button2);
        mybutton1.setOnClickListener(this);
        mybutton2.setOnClickListener(this);

    }
    //接收账号信息
    public void recive(){
        Intent intent = getIntent();
        name = intent.getStringExtra("zhangh");
        mima = intent.getStringExtra("mima");
        myname.setText("用户："+name);
        myzhangh.setText(name);
        mymima.setText(mima);
    }
    //点击事件
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
                if (myxmima.getText().toString().equals("")){
                    Toast.makeText(this,"请输入修改后的密码！",Toast.LENGTH_SHORT).show();
                }else if(myxmima.getText().toString().equals(mima)){
                    Toast.makeText(this,"与原密码相同，修改失败！",Toast.LENGTH_SHORT).show();
                }else {
                    xgmima(myxmima.getText().toString().trim()+".cn");
                    Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this,ZhujmActivity.class);
                intent2.putExtra("zhangh",name);
                intent2.putExtra("mima",mima);
                startActivity(intent2);
                break;
        }
    }
    public void xgmima(String xmima){
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name,xmima);
        editor.commit();
    }
}
