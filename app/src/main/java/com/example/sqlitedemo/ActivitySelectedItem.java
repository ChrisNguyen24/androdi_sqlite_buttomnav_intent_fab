package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sqlitedemo.model.Cong;

public class ActivitySelectedItem extends AppCompatActivity {

    private EditText etName,etMark,etId;
    private RadioButton rb1,rb2;
    private SQLiteStudentHelper sql;
    private Button btUpdate,btDel,btCanc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);
        sql = new SQLiteStudentHelper(this);
        etId = findViewById(R.id.seId);
        etName = findViewById(R.id.seName);
        etMark = findViewById(R.id.seMark);
        rb1 = findViewById(R.id.seRb1);
        rb2 = findViewById(R.id.seRb2);
        btUpdate = findViewById(R.id.seBtUpdate);
        btDel = findViewById(R.id.seBtDel);
        btCanc = findViewById(R.id.seBtCancel);


        Intent intent = getIntent();
        Cong c = (Cong) intent.getSerializableExtra("item");
        etId.setText(String.valueOf(c.getId()));
        etName.setText(String.valueOf(c.getName()));
        etMark.setText(String.valueOf(c.getMark()));
        if(c.isGender()){
            rb1.setChecked(true);
        }
        else rb2.setChecked(true);
        Toast.makeText(getApplicationContext(),"selected "+c.getName(),Toast.LENGTH_SHORT).show();

        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.deleteById(Integer.parseInt(etId.getText().toString()));
                Toast.makeText(getApplicationContext(),"deleted "+c.getName(),Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cong co = new Cong(Integer.parseInt(etId.getText().toString()), etName.getText().toString(),true,Double.parseDouble(etMark.getText().toString()));
                sql.updateStudent(co);
                Toast.makeText(getApplicationContext(),"updated "+co.getId(),Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btCanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}