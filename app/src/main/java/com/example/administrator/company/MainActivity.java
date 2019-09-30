package com.example.administrator.company;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import org.json.JSONArray;

import data_treating.SelectByCompany;

public class MainActivity extends AppCompatActivity implements TextView.OnClickListener{

    public static String company;
    public static String password;
    private MainActivity mContext;
    private ResideMenu resideMenu;
    private ResideMenuItem item_School;
    private ResideMenuItem item_Scoietr;
    private ResideMenuItem item_Rocard;
    private ResideMenuItem item_Setting;
    private TextView main_bar;
    private int a = 0;

    public static String select_json;
    public static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 接收LoginActivity传输来的登录用户ID
        Intent i = getIntent();
        company = i.getStringExtra("company");
        password = i.getStringExtra("password");

        mContext = this;
        setUpMenu();
        if (savedInstanceState==null){
            changeFragment(new School());
        }

    }

    private void setUpMenu(){

        resideMenu = new ResideMenu(this);

        resideMenu.setBackground(R.drawable.backc);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        resideMenu.setScaleValue(0.5f);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        item_School = new ResideMenuItem(this,R.drawable.school,"校招发布");
        item_Scoietr = new ResideMenuItem(this,R.drawable.shehui,"社招发布");
        item_Rocard = new ResideMenuItem(this,R.drawable.jilu,"发布记录");
        item_Setting = new ResideMenuItem(this,R.drawable.set,"个人设置");

        item_Setting.setOnClickListener(this);
        item_Rocard.setOnClickListener(this);
        item_School.setOnClickListener(this);
        item_Scoietr.setOnClickListener(this);


        resideMenu.addMenuItem(item_School,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item_Scoietr,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item_Rocard,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(item_Setting,ResideMenu.DIRECTION_LEFT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });


    }

    public  boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    private void setBar(int a){
        main_bar=findViewById(R.id.main_bar);
        main_bar.setTextColor(Color.WHITE);
        if (a==0){
            main_bar.setText("校招发布");
        }
        if(a==1){
            main_bar.setText("社招发布");
        }
        if(a==2){
            main_bar.setText("发布记录");
        }
        if(a==3){
            main_bar.setText("个人设置");
        }


    }

    @Override
    public void onClick(View view) {

        if (view==item_School){
            changeFragment(new School());
            setBar(0);
        }else if(view==item_Scoietr){
            changeFragment(new Society());
            setBar(1);
        }else if(view==item_Rocard){
            new UpRocardTask(company).execute();
        }else if (view==item_Setting){
            changeFragment(new User_set1());
            setBar(3);
        }
        resideMenu.closeMenu();
    }
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {

        }

        @Override
        public void closeMenu() {

        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public class UpRocardTask extends AsyncTask<Void, Void, Boolean> {

        private final String mcompany;

        UpRocardTask(String company) {
            mcompany = company;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                select_json = new SelectByCompany(mcompany).getSelect_json();
                JSONArray jsonArray = new JSONArray(select_json);
                count = jsonArray.length();
                if (count > 0){
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
                Toast.makeText(MainActivity.this, "查询记录成功！",Toast.LENGTH_SHORT).show();
                changeFragment(new Up_rocard());
                setBar(2);
            } else {
                Toast.makeText(MainActivity.this, "暂无推送记录！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

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
