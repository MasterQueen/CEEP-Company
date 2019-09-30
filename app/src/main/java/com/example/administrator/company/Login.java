package com.example.administrator.company;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data_treating.LoginCompany;

/**
 * Created by Administrator on 6/12/2018.
 */

public class Login extends AppCompatActivity implements View.OnClickListener {


    private EditText company_name;
    private EditText et_password;
    private Button login;
    private Button register;
    private String company;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                View decorView = window.getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.setNavigationBarColor(Color.TRANSPARENT);

            }else {
                Window window = this.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);


            }
        }

        //获取View对象

        company_name = findViewById(R.id.company_name);

        et_password = findViewById(R.id.password);

        login = findViewById(R.id.login);

        login.setOnClickListener(this);

        register = findViewById(R.id.register);

        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.login:

                company = company_name.getText().toString();
                password = et_password.getText().toString();
                new UserLoginTask(company, password).execute();

                break;

            case R.id.register:

                 Intent intent = new Intent(Login.this,Register.class);
                 startActivity(intent);

                break;

        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mcompany;
        private final String mpassword;

        UserLoginTask(String university, String password) {
            mcompany = university;
            mpassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_login = new LoginCompany(mcompany, mpassword).getIs_login();
                System.out.println(is_login+mcompany+mpassword);
                if (is_login.equals("1")){
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
                Toast.makeText(Login.this, "登录成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this,MainActivity.class);
                intent.putExtra("company", mcompany);
                intent.putExtra("password",password);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this, "登录失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

    /**
     * 使得按下返回就会跟按下Home键操作一致
     * 启动界面在第一次启动会显示出来
     * 按下返回键回到桌面再次进入就不会显示了
     * 除非程序被杀死或退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            Intent i= new Intent(Intent.ACTION_MAIN);  //主启动，不期望接收数据

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);       //新的activity栈中开启，或者已经存在就调到栈前

            i.addCategory(Intent.CATEGORY_HOME);            //添加种类，为设备首次启动显示的页面

            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }
}
