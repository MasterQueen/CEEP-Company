package com.example.administrator.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 7/24/2018.
 */

public class User_set1 extends Fragment {

    private EditText company_name;
    private EditText password;
    private Button ok;
    private Button change;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_set1,null);

        ok = view.findViewById(R.id.set_ok);
        change = view.findViewById(R.id.set_change);
        company_name = view.findViewById(R.id.set_company_edit);
        company_name.setText(MainActivity.company);
        password = view.findViewById(R.id.set_password_edit);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(company_name.getText().toString().equals(MainActivity.company)){

                    if (password.getText().toString().equals(MainActivity.password)){

                        Intent intent = new Intent(getContext(),User_set2.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(getContext(),"密码输入有误",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(),"账号输入有误",Toast.LENGTH_SHORT).show();
                }


            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Login.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
