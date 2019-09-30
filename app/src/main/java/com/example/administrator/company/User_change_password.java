package com.example.administrator.company;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data_treating.User_setNew;

/**
 * Created by Administrator on 7/24/2018.
 */

public class User_change_password extends AppCompatActivity {

    private EditText old_password;
    private EditText new_passsword;
    private EditText again_passwprd;
    private Button ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.set_change_password);

        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.change_password);
        toolbar.setTitle("修改密码");
        toolbar.setTitleTextColor(Color.WHITE);

        old_password = findViewById(R.id.old_password);
        new_passsword = findViewById(R.id.new_password);
        again_passwprd = findViewById(R.id.new_password_again);



        ok = findViewById(R.id.change_password_ok);



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (old_password.getText().toString().equals(MainActivity.password)){
                    if (new_passsword.getText().toString().equals(again_passwprd.getText().toString())){
                        //修改密码。退回登陆界面
                        String company = MainActivity.company;
                        String password = new_passsword.getText().toString();
                        new ReSetTask(company, password).execute();
                    }else{

                        Toast.makeText(User_change_password.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(User_change_password.this,"密码输入有误",Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

    public class ReSetTask extends AsyncTask<Void, Void, Boolean> {

        private final String mcompany;
        private final String mpassword;


        ReSetTask(String company, String password) {
            mcompany = company;
            mpassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_set = new User_setNew(mcompany, mpassword).getIs_set();
                System.out.println(is_set+mcompany+mpassword);
                if (is_set.equals("1")){
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
                Toast.makeText(User_change_password.this, "修改成功！",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(User_change_password.this,Login.class));
            } else {
                Toast.makeText(User_change_password.this, "修改失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
