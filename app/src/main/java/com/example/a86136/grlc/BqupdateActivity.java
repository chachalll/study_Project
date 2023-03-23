package com.example.a86136.grlc;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BqupdateActivity extends AppCompatActivity implements View.OnClickListener{
    String name;
    String mima;
    String id;
    String time;
    String note;
    private EditText myintime;
    private EditText myinnote;
    private Button myfh;
    private Button mybutton1;
    MyHelper myHelper = new MyHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bqupdate);
        recive();
        myfind();
    }
    public void myfind(){
        myintime = (EditText)findViewById(R.id.intime);
        myinnote = (EditText)findViewById(R.id.innote);
        myfh = (Button)findViewById(R.id.fh);
        mybutton1 = (Button)findViewById(R.id.button1);
        myfh.setOnClickListener(this);
        mybutton1.setOnClickListener(this);
        myintime.setText(time);
        myinnote.setText(note);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.fh:
                Intent intent1 = new Intent(this,BqmangerActivity.class);
                intent1.putExtra("zhangh",name);
                intent1.putExtra("mima",mima);
                startActivity(intent1);
                break;
            case R.id.button1:
                time = myintime.getText().toString().trim();
                note = myinnote.getText().toString().trim();
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(this).setTitle("Dialog对话框")
                        .setMessage("是否修改?")//设置提示信息
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                update();
                                Toast.makeText(BqupdateActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(BqupdateActivity.this,BqmangerActivity.class);
                                intent1.putExtra("zhangh",name);
                                intent1.putExtra("mima",mima);
                                startActivity(intent1);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create();
                dialog.show();
                break;

        }
    }
    public void update(){
        SQLiteDatabase db = myHelper.getReadableDatabase();
        String sql = "update notes set time=?,note=? where _id=?";
        db.execSQL (sql,new String[]{myintime.getText().toString().trim(),myinnote.getText().toString().trim(),id});
        db.close();
    }
    public void recive(){
        Intent intent = getIntent();
        name = intent.getStringExtra("zhangh");
        mima = intent.getStringExtra("mima");
        id = intent.getStringExtra("id");
        time = intent.getStringExtra("times");
        note = intent.getStringExtra("notes");
    }
}
