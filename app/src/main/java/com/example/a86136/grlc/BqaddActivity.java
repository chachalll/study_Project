package com.example.a86136.grlc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BqaddActivity extends AppCompatActivity implements View.OnClickListener{
    String name;
    String mima;
    String time;
    String note;
    private EditText myintime;
    private EditText myinnote;
    private Button myfh;
    private Button mybutton1;
    MyHelper mHelper = new MyHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bqadd);
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
                insert(name,time,note);
                Intent intent2 = new Intent(this,BqmangerActivity.class);
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
    public void insert(String name,String time, String note){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("time",time);
        values.put("note",note);
        db.insert("notes",null,values);
       Toast.makeText(this,"保存成功！",Toast.LENGTH_SHORT).show();
        db.close();
    }
}
