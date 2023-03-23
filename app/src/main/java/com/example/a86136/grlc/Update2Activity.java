package com.example.a86136.grlc;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Update2Activity extends AppCompatActivity implements View.OnClickListener{
    String[] spin2 = {"工资","兼职","生活费", "礼物"};
    String name;
    String mima;
    String id;
    String moneys;
    String times;
    String leibs;
    String payers;
    String others;
    private TextView myxmoney;
    private TextView myxtime;
    private TextView myxleib;
    private TextView myxpayer;
    private TextView myxother;
    private EditText mymoney;
    private EditText mytime;
    private Spinner myspin2;
    private EditText mypayer;
    private EditText myother;
    private Button myupdate;
    private Button myfh;

    MyHelper myHelper = new MyHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);
        recive();
        myfind();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,spin2);
        myspin2.setAdapter(adapter);
    }
    public void myfind(){
        myxmoney = (TextView)findViewById(R.id.xmoney);
        myxtime = (TextView)findViewById(R.id.xtime);
        myxleib = (TextView)findViewById(R.id.xleib);
        myxpayer = (TextView)findViewById(R.id.xpayer);
        myxother = (TextView)findViewById(R.id.xother);
        mymoney = (EditText)findViewById(R.id.money);
        mytime = (EditText)findViewById(R.id.time);
        myspin2 = (Spinner)findViewById(R.id.spin2);
        mypayer = (EditText)findViewById(R.id.payer);
        myother = (EditText)findViewById(R.id.other);
        myupdate = (Button)findViewById(R.id.update);
        myfh = (Button)findViewById(R.id.fh);
        myupdate.setOnClickListener(this);
        myfh.setOnClickListener(this);
        myxmoney.setText("金额："+moneys);
        myxtime.setText("时间："+times);
        myxleib.setText("类别："+leibs);
        myxpayer.setText("收款方："+payers);
        myxother.setText("备注："+others);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(this).setTitle("Dialog对话框")
                        .setMessage("是否修改?")//设置提示信息
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                update();
                                Toast.makeText(Update2Activity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                                Intent intent2 = new Intent(Update2Activity.this,MyshouruActivity.class);
                                intent2.putExtra("zhangh",name);
                                intent2.putExtra("mima",mima);
                                startActivity(intent2);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create();
                dialog.show();
                break;
            case R.id.fh:
                Intent intent2 = new Intent(this,MyshouruActivity.class);
                intent2.putExtra("zhangh",name);
                intent2.putExtra("mima",mima);
                startActivity(intent2);
                break;
        }
    }
    public void update(){
        SQLiteDatabase db = myHelper.getReadableDatabase();
        String sql = "update income set money=?,time=?,leib=?, payer=?, other=? where _id=?";
        db.execSQL (sql,new String[]{mymoney.getText().toString().trim(),mytime.getText().toString().trim(),
                myspin2.getSelectedItem().toString().trim(), mypayer.getText().toString().trim(), myother.getText().toString().trim(),id});
        db.close();
    }
    public void recive(){
        Intent intent = getIntent();
        name = intent.getStringExtra("zhangh");
        mima = intent.getStringExtra("mima");
        id = intent.getStringExtra("id");
        moneys = intent.getStringExtra("moneys");
        times = intent.getStringExtra("times");
        leibs = intent.getStringExtra("leibs");
        payers = intent.getStringExtra("payers");
        others = intent.getStringExtra("others");
    }
}
