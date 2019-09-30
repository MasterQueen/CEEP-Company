package com.example.administrator.company;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data_treating.DeleteCompany;

/**
 * Created by Administrator on 7/24/2018.
 */

public class User_set2 extends AppCompatActivity {

    private TextView company_name;
    private Button logout;
    private Button change_password;
    private  String company;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_set2);

        company_name=findViewById(R.id.company_name);
        logout = findViewById(R.id.logout);
        change_password = findViewById(R.id.change_password);

        company_name.setText(MainActivity.company);


        //注销用户
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  AlertDialog.Builder(User_set2.this)
                        .setTitle("注销用户")
                        .setMessage("注销将删除与用户有关的所有内容，是否继续？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //选择确定注销，，删除所有信息并返回登陆界面
                                company = MainActivity.company;
                                new UserDeleteTask(company).execute();

                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //取消注销，返回


                            }
                        })
                        .show();

            }
        });

        //更改密码
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_set2.this,User_change_password.class);
                startActivity(intent);
            }
        });
    }

    public class UserDeleteTask extends AsyncTask<Void, Void, Boolean> {

        private final String mcompany;

        UserDeleteTask(String university) {
            mcompany = company;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_delete = new DeleteCompany(mcompany).getIs_delete();
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
                Toast.makeText(User_set2.this, "注销成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(User_set2.this,Login.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(User_set2.this, "注销失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
