package com.example.administrator.company;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import data_treating.Add;

/**
 * Created by Administrator on 7/19/2018.
 */

public class School extends Fragment implements View.OnClickListener{
    private View view;
    private EditText edit_postion;
    private EditText edit_request;
    private EditText edit_address;
    private EditText edit_time;
    private EditText edit_people;
    private Button ok;
    String postion;
    String request;
    String address;
    String time;
    String people;
    private String company = MainActivity.company;
    private String mark = "校招";
    private String theDate;
    private String theDateTime;
    private String sendtime;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.school_layout,null);

        setView(view);
        onClick(view);
        return view;

    }

    public void setView(View view){

        edit_postion = view.findViewById(R.id.school_postion);
        edit_request = view.findViewById(R.id.school_requset);
        edit_address = view.findViewById(R.id.school_address);
        edit_time  = view.findViewById(R.id.school_time);
        edit_people = view.findViewById(R.id.school_people);
        ok = view.findViewById(R.id.school_ok);

        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);

        final int month = calendar.get(Calendar.MONTH);

        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        sendtime = String.format("%d-%02d-%02d",year,month+1,day);

        edit_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        theDateTime = theDate + " " + String.format("%02d:%02d",hourOfDay,minute);
                        edit_time.setText(theDateTime);
                    }
                },0,0,true).show();
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        theDate = String.format("%d-%02d-%02d",year,month+1,dayOfMonth);
//                        iEditText_time.setText(theDate);
                    }
                },year,month,day).show();
            }
        });

        ok.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        int id = view.getId();

        view=this.getView();
        switch (id){



            case R.id.school_ok:


                postion = edit_postion.getText().toString();
                request = edit_request.getText().toString();
                address = edit_address.getText().toString();
                time = edit_time.getText().toString();
                people = edit_people.getText().toString();

                if (postion.equals("")&&request.equals("")&&address.equals("")&&time.equals("")&&people.equals("")){
                    Toast.makeText(getContext(), "请输入所有信息！",Toast.LENGTH_SHORT).show();
                }else {
                    //添加到数据库
                    new AddMessageTask(postion, request, address, time, people, mark, sendtime).execute();

                }

                break;
            default:

                break;


        }

    }

    public class AddMessageTask extends AsyncTask<Void, Void, Boolean> {

        private final String mpostion;
        private final String mrequest;
        private final String maddress;
        private final String mtime;
        private final String mpeople;
        private final String mmark;
        private final String msendtime;

        AddMessageTask(String postion, String request, String address, String time, String people, String mark, String sendtime) {
            mpostion = postion;
            mrequest = request;
            maddress = address;
            mtime = time;
            mpeople = people;
            mmark = mark;
            msendtime = sendtime;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_add = new Add(company, mpostion, mrequest, maddress, mtime, mpeople, mmark, msendtime).getIs_add();
                if (is_add.equals("1")){
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
                Toast.makeText(getContext(), "发布成功！",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(School_news.this,User_school.class);
//                startActivity(intent);
//                finish();
                // 提交成功后清空文本框
                edit_postion = view.findViewById(R.id.school_postion);
                edit_request = view.findViewById(R.id.school_requset);
                edit_address = view.findViewById(R.id.school_address);
                edit_time  = view.findViewById(R.id.school_time);
                edit_people = view.findViewById(R.id.school_people);
                edit_postion.setText("");
                edit_request.setText("");
                edit_address.setText("");
                edit_time.setText("");
                edit_people.setText("");
            } else {
                Toast.makeText(getContext(), "发布失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
