package com.example.sqlitedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sqlitedemo.model.Cong;

import java.util.List;


public class ActivityCreateStudent extends AppCompatActivity {
    private Button btAdd,btCacel;
    private EditText etName,etMark;
    private RadioButton rb1,rb2;

    private SQLiteStudentHelper sqli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        btAdd = findViewById(R.id.cBtAdd);
        btCacel = findViewById(R.id.cBtCancel);
        etName = findViewById(R.id.cName);
        etMark = findViewById(R.id.cMark);
        rb1 = findViewById(R.id.cRb1);
        sqli = new SQLiteStudentHelper(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Added success", Toast.LENGTH_SHORT).show();
//                Cong c = new Cong(2,"Han",false,9);
                System.out.println("test"+rb1.isChecked());
                Cong c = new Cong(etName.getText().toString(),rb1.isChecked(), Double.parseDouble(etMark.getText().toString()));
                sqli.addSutdent(c);
                finish();


            }
        });
        btCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}