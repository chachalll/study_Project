package com.example.a86136.grlc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Xzsr2Activity extends AppCompatActivity implements View.OnClickListener{

    String[] spin2 = {"工资","兼职","生活费", "礼物"};
    String name;
    String mima;
    String money;
    String time;
    String leib;
    String payer;
    String other;
    private Button mybutton1;
    private Button mybutton2;
    private TextView myzhangh;
    private Spinner myspin2;
    private EditText mymonwy;
    private EditText mytime;
    private EditText mypayer;
    private EditText myother;
    MyHelper myHelper = new MyHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xzsr);
        myfind();
        recive();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,spin2);
        myspin2.setAdapter(adapter);

    }
    public void myfind(){
        myspin2 = (Spinner)findViewById(R.id.spin2);
        mybutton1 = (Button)findViewById(R.id.button1);
        mybutton2 = (Button)findViewById(R.id.button2);
        myzhangh = (TextView)findViewById(R.id.zhangh) ;
        mymonwy = (EditText)findViewById(R.id.money);
        mytime = (EditText)findViewById(R.id.time);
        mypayer = (EditText)findViewById(R.id.payer);
        myother = (EditText)findViewById(R.id.other);
        mybutton1.setOnClickListener(this);
        mybutton2.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
                money = mymonwy.getText().toString().trim();
                time = mytime.getText().toString().trim();
                leib = myspin2.getSelectedItem().toString();
                payer = mypayer.getText().toString().trim();
                other = myother.getText().toString().trim();
                insert(name, money , time, leib, payer, other);
                Toast.makeText(this,"保存成功！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this,MyshouruActivity.class);
                intent2.putExtra("zhangh",name);
                intent2.putExtra("mima",mima);
                startActivity(intent2);
                break;
        }
    }
    public void recive(){
        Intent intent = getIntent();
        name = intent.getStringExtra("zhangh");
        mima = intent.getStringExtra("mima");
    }
    public void insert(String name, String money, String time, String leib,String payer, String other){
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("money",money);
        values.put("time",time);
        values.put("leib", leib);
        values.put("payer", payer);
        values.put("other",other);
        db.insert("income",null,values);
        db.close();
    }
}
