package com.example.administrator.company;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import data_treating.RegisterCompany;

/**
 * Created by Administrator on 7/30/2018.
 */

public class User_agreemaent extends AppCompatActivity {

    private TextView textview;
    private CheckBox agreement;
    private Button ok;
    private  TextView title;


    private String company;
    private String intro;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_agreemaent);

        textview =findViewById(R.id.Textview);
        agreement = findViewById(R.id.agreement);
        ok = findViewById(R.id.ok);
        title = findViewById(R.id.title);

        Intent i = getIntent();

        company = i.getStringExtra("company");
        intro = i.getStringExtra("intro");
        password = i.getStringExtra("password");


        agreement.setOnClickListener(new MyCheckBoxOnClickListener());
        ok.setOnClickListener(new MyButtonOnClickListener());



    }

    final class MyCheckBoxOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if (agreement.isChecked()){
                ok.setEnabled(true);
            }
            else{
                ok.setEnabled(false);
            }
        }
    }

    final class MyButtonOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {



            new UserRegisterTask(company, intro, password).execute();

        }
    }
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mcompany;
        private final String mintro;
        private final String mpassword;

        UserRegisterTask(String company, String intro, String password) {


            mcompany = company;
            mintro = intro;
            mpassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {



            try {
                String is_register = new RegisterCompany(mcompany, mintro, mpassword).getIs_register();
                System.out.println(is_register+mcompany+mintro+mpassword);
                if (is_register.equals("1")){
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
                Toast.makeText(User_agreemaent.this, "注册成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(User_agreemaent.this,Login.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(User_agreemaent.this, "企业已注册或注册失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

}
