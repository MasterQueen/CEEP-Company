package com.example.administrator.company;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data_treating.RegisterCompany;

/**
 * Created by Administrator on 7/24/2018.
 */

public class Register extends AppCompatActivity {
    EditText company_name;
    EditText company_intro;
    EditText company_password;
    Button register;
    String company;
    String intro;
    String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        company_intro=findViewById(R.id.edit_intro);
        company_name = findViewById(R.id.edit_company_name);
        company_password=findViewById(R.id.edit_password);

        register = findViewById(R.id.register_ok);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (company_intro.getText().toString().equals("")&&company_name.getText().toString().equals("")&&company_password.getText().toString().equals("")){
                    Toast.makeText(Register.this, "请输入所有信息",Toast.LENGTH_SHORT).show();
                }else {
                    //注册

                    Intent intent = new Intent(Register.this,User_agreemaent.class);

                    company = company_name.getText().toString();
                    intro = company_intro.getText().toString();
                    password = company_password.getText().toString();

                    intent.putExtra("company",company);
                    intent.putExtra("intro",intro);
                    intent.putExtra("password",password);


                    startActivity(intent);
                    finish();

//                    new UserRegisterTask(company, intro, password).execute();
                }
            }
        });

    }
//
//    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mcompany;
//        private final String mintro;
//        private final String mpassword;
//
//        UserRegisterTask(String company, String intro, String password) {
//            mcompany = company;
//            mintro = intro;
//            mpassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//
//            try {
//                String is_register = new RegisterCompany(mcompany, mintro, mpassword).getIs_register();
//                System.out.println(is_register+mcompany+mintro+mpassword);
//                if (is_register.equals("1")){
//                    return true;
//                }
//
//            } catch (Exception e) {
//                Log.i("ok", "有错误！");
//            }
//
//            return false;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//
//            if (success) {
//                Toast.makeText(Register.this, "注册成功！",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Register.this,Login.class);
//                startActivity(intent);
//                finish();
//            } else {
//                Toast.makeText(Register.this, "企业已注册或注册失败！",Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//
//        }
//    }
}
