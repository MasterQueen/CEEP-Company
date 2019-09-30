package com.example.administrator.company;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.HardwarePropertiesManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data_treating.DeleteMessage;

/**
 * Created by Administrator on 7/24/2018.
 */

public class Up_rocard_massage extends AppCompatActivity  {

    TextView postion;
    TextView postion_conter;
    TextView request;
    TextView request_conter;
    TextView address;
    TextView address_conter;
    TextView time;
    TextView time_conter;
    TextView people;
    TextView people_conter;
    Button delect;

    String mpostion;
    String mrequest;
    String maddress;
    String mtime;
    String mpeople;
    String company;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rocard_massage);

        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.up_rocard_toolbar);
        toolbar.setTitle("详细内容");
        toolbar.setTitleTextColor(Color.WHITE);

        Intent i = getIntent();
        mpostion = i.getStringExtra("postion");
        mrequest = i.getStringExtra("request");
        maddress = i.getStringExtra("address");
        mtime = i.getStringExtra("time");
        mpeople = i.getStringExtra("people");


        postion=findViewById(R.id.postion);
        postion_conter=findViewById(R.id.postion_conter);
        request=findViewById(R.id.request);
        request_conter=findViewById(R.id.request_conter);
        address = findViewById(R.id.address);
        address_conter = findViewById(R.id.address_conter);
        time=findViewById(R.id.time);
        time_conter=findViewById(R.id.time_conter);
        people = findViewById(R.id.people);
        people_conter=findViewById(R.id.people_conter);

        postion_conter.setText(mpostion);
        request_conter.setText(mrequest);
        address_conter.setText(maddress);
        time_conter.setText(mtime);
        people_conter.setText(mpeople);
        System.out.println(mpostion+mrequest+maddress+mtime+mpeople);

        delect=findViewById(R.id.delect);

        delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                company = MainActivity.company;
                new MessageDeleteTask(company, maddress, mtime, mpeople).execute();
            }
        });

    }

    public class MessageDeleteTask extends AsyncTask<Void, Void, Boolean> {

        private final String mcompany;
        private final String maddress;
        private final String mtime;
        private final String mpeople;

        MessageDeleteTask(String company, String address, String time, String people) {
            mcompany = company;
            maddress = address;
            mtime = time;
            mpeople = people;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_delete = new DeleteMessage(mcompany, maddress, mtime, mpeople).getIs_delete();
                if (is_delete.equals("1")){
                    return true;
                }

            } catch (Exception e) {
                Log.i("ok", "有错误！");
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                Toast.makeText(Up_rocard_massage.this, "删除成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Up_rocard_massage.this,MainActivity.class);
                intent.putExtra("company", MainActivity.company);
                intent.putExtra("password", MainActivity.password);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Up_rocard_massage.this, "删除失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
