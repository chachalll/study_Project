package com.example.a86136.grlc;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ZhujmActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView myi1;
    private ImageView myi2;
    private ImageView myi3;
    private ImageView myi4;
    private ImageView myi6;
    private ImageView myi7;
    private ImageView myi8;
    private TextView myzhangh;
    String name;
    String mima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhujm);
        myfind();
        recive();

    }
    public void myfind(){
        myi1 = (ImageView)findViewById(R.id.i1);
        myi2 = (ImageView)findViewById(R.id.i2);
        myi3 = (ImageView)findViewById(R.id.i3);
        myi4 = (ImageView)findViewById(R.id.i4);
        myi6 = (ImageView)findViewById(R.id.i6);
        myi7 = (ImageView)findViewById(R.id.i7);
        myi8 = (ImageView)findViewById(R.id.i8);
        myzhangh = (TextView)findViewById(R.id.zhangh);
        myi1.setOnClickListener(this);
        myi2.setOnClickListener(this);
        myi3.setOnClickListener(this);
        myi4.setOnClickListener(this);
        myi6.setOnClickListener(this);
        myi7.setOnClickListener(this);
        myi8.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.i1:
                Intent intent1 = new Intent(ZhujmActivity.this,XzzcActivity.class);
                intent1.putExtra("zhangh",name);
                intent1.putExtra("mima",mima);
                startActivity(intent1);
                break;
            case R.id.i2:
                Intent intent2 = new Intent(ZhujmActivity.this,XzsrActivity.class);
                intent2.putExtra("zhangh",name);
                intent2.putExtra("mima",mima);
                startActivity(intent2);
                break;
            case R.id.i3:
                Intent intent3 = new Intent(ZhujmActivity.this,MyzhichuActivity.class);
                intent3.putExtra("zhangh",name);
                intent3.putExtra("mima",mima);
                startActivity(intent3);
                break;
            case R.id.i4:
                Intent intent4 = new Intent(ZhujmActivity.this,MyshouruActivity.class);
                intent4.putExtra("zhangh",name);
                intent4.putExtra("mima",mima);
                startActivity(intent4);
                break;
            case R.id.i6:
                Intent intent6 = new Intent(ZhujmActivity.this,XtszActivity.class);
                intent6.putExtra("zhangh",name);
                intent6.putExtra("mima",mima);
                startActivity(intent6);
                break;
            case R.id.i7:
                Intent intent7 = new Intent(ZhujmActivity.this,BqmangerActivity.class);
                intent7.putExtra("zhangh",name);
                intent7.putExtra("mima",mima);
                startActivity(intent7);
                break;
            case R.id.i8:
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(this).setTitle("Dialog对话框")
                        .setMessage("是否退出?")//设置提示信息
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent8 = new Intent(ZhujmActivity.this,MainActivity.class);
                                startActivity(intent8);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create();
                dialog.show();
                break;

        }
    }
    //接收来自MainActivity的数据
    public void recive(){
        Intent intent = getIntent();
        name = intent.getStringExtra("zhangh");
        mima = intent.getStringExtra("mima");
        myzhangh.setText("用户"+name+"欢迎您！");

    }
}
