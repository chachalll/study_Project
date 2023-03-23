package com.example.a86136.grlc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyshouruActivity extends AppCompatActivity implements View.OnClickListener{
    String name;
    String mima;
    Come income = new Come();
    List<Come> list = new ArrayList<Come>();//泛型come
    private Button myfh;
    private  Button myadd;
    private Button mychaxun;
    private EditText myintime;
    private EditText myleib;
    private ListView mylv;
    MyHelper myHelper = new MyHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshouru);
        recive();
        myfind();
        Myadapter myadapter = new Myadapter();
        list = findall();
        mylv.setAdapter(myadapter);

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.chaxun:
                Myadapter myadapter = new Myadapter();
                list = find();
                mylv.setAdapter(myadapter);
                break;
            case R.id.fh:
                Intent intent2 = new Intent(this,ZhujmActivity.class);
                intent2.putExtra("zhangh",name);
                intent2.putExtra("mima",mima);
                startActivity(intent2);
                break;
            case R.id.add:
                Intent intent3 = new Intent(this,Xzsr2Activity.class);
                intent3.putExtra("zhangh",name);
                intent3.putExtra("mima",mima);
                startActivity(intent3);
                break;
        }
    }
    public void myfind(){
        myfh = (Button)findViewById(R.id.fh);
        mychaxun = (Button)findViewById(R.id.chaxun);
        myadd = (Button)findViewById(R.id.add);
        myintime = (EditText)findViewById(R.id.intime);
        myleib = (EditText)findViewById(R.id.inleib) ;
        mylv = (ListView)findViewById(R.id.lv);
        myfh.setOnClickListener(this);
        mychaxun.setOnClickListener(this);
        myadd.setOnClickListener(this);

    }
    //查询income中的所有数据
    public List<Come> findall(){
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income where name=?", new String[]{name});
        if(cursor.getCount() == 0){
        }else {
            while (cursor.moveToNext()) {
                Come comitem = new Come();
                String mid = cursor.getString(0);
                String mtime = cursor.getString(3);
                String mleib = cursor.getString(4);
                String mmoney = cursor.getString(2);
                String mpayer = cursor.getString(5);
                String mother = cursor.getString(6);
                comitem .setId(mid);
                comitem .setTime(mtime);
                comitem .setLeib(mleib);
                comitem .setMoney(mmoney);
                comitem .setPayer(mpayer);
                comitem .setOther(mother);
                list.add(comitem);
            }
        }
        db.close();
        cursor.close();
        return list;
    }
    //查询部分数据
    public List<Come> find(){
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor;
        if(myleib.getText().toString().equals("")&&myleib.getText().toString().equals("")){
           cursor = db.rawQuery("select * from income where name=?", new String[]{name});
        } else if(myleib.getText().toString().equals("")){
            cursor = db.rawQuery("select * from income where name=? time=?", new String[]{name,myintime.getText().toString().trim()});
        }else{
            cursor = db.rawQuery("select * from income where name=? leib=?", new String[]{name,myleib.getText().toString().trim()});
        }
        List<Come> list = new ArrayList<Come>();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"无相关信息！",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                Come comitem = new Come();
                String mid = cursor.getString(0);
                String mtime = cursor.getString(3);
                String mleib = cursor.getString(4);
                String mmoney = cursor.getString(2);
                String mpayer = cursor.getString(5);
                String mother = cursor.getString(6);
                comitem .setId(mid);
                comitem .setTime(mtime);
                comitem .setLeib(mleib);
                comitem .setMoney(mmoney);
                comitem .setPayer(mpayer);
                comitem .setOther(mother);
                list.add(comitem);
            }
        }
        db.close();
        cursor.close();
        return list;
    }
    public void delete(String id) {
        SQLiteDatabase db = myHelper.getReadableDatabase();
        db.execSQL("delete from income where _id=?",new String[]{id});
        db.close();
    }
    //
    public  class Myadapter extends BaseAdapter{
        @Override
        public int getCount(){
            return list.size();
        }
        @Override
        public Object getItem(int position){
            return position;
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            View view = View.inflate(MyshouruActivity.this, R.layout.lv_item,null);
            TextView mytime = (TextView)view.findViewById(R.id.time);
            TextView myleib = (TextView)view.findViewById(R.id.leib);
            TextView mymoney = (TextView)view.findViewById(R.id.money);
            TextView mypayer = (TextView) view.findViewById(R.id.payer);
            Button mydelete = (Button)view.findViewById(R.id.delete) ;
            Button myupdate = (Button)view.findViewById(R.id.update) ;
            mydelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(list.get(position).getId());
                    Toast.makeText(MyshouruActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                    Myadapter madapter = new Myadapter();
                    list = find();
                    mylv.setAdapter(madapter);
                }
            });
            myupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent4 = new Intent(MyshouruActivity.this,Update2Activity.class);
                    intent4.putExtra("zhangh",name);
                    intent4.putExtra("mima",mima);
                    intent4.putExtra("id",list.get(position).getId());
                    intent4.putExtra("moneys",list.get(position).getMoney());
                    intent4.putExtra("times",list.get(position).getTime());
                    intent4.putExtra("leibs",list.get(position).getLeib());
                    intent4.putExtra("payers",list.get(position).getPayer());
                    intent4.putExtra("others",list.get(position).getOther());
                    startActivity(intent4);
                }
            });
            mytime.setText(list.get(position).getTime());
            myleib.setText(list.get(position).getLeib());
            mymoney.setText(list.get(position).getMoney());
            mypayer.setText(list.get(position).getPayer());
            return view;
        }
    }
    public void recive(){
        Intent intent = getIntent();
        name = intent.getStringExtra("zhangh");
        mima = intent.getStringExtra("mima");
    }
}
