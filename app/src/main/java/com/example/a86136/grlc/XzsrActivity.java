package com.example.a86136.grlc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class XzsrActivity extends AppCompatActivity implements View.OnClickListener{
  //  String[] year = {"2020","2021","2022","2023","2024"};
  //  String[] month = {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
  //  String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
      //      "20","21","22","23","24","25","26","27","28","29","30"};
   // String[] day2 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
    //        "20","21","22","23","24","25","26","27","28","29","30"};
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
    private Spinner myspin2;
    //private Spinner myyear;
 //   private Spinner mymonth;
  //  private Spinner myday;
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
   //   ArrayAdapter<String> yearadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,year);

    }
    public void myfind(){
        myspin2 = (Spinner)findViewById(R.id.spin2);
    //    myyear = (Spinner)findViewById(R.id.year);
     //   mymonth = (Spinner)findViewById(R.id.month);
     //   myday  = (Spinner)findViewById(R.id.day);
        mybutton1 = (Button)findViewById(R.id.button1);
        mybutton2 = (Button)findViewById(R.id.button2);
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
                Intent intent2 = new Intent(this,ZhujmActivity.class);
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
