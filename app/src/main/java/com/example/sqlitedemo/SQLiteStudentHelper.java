package com.example.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitedemo.model.Cong;

import java.util.ArrayList;
import java.util.List;

public class SQLiteStudentHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "studentDB.db";
    private static final int DB_Version = 1;

    public SQLiteStudentHelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang
        String sqlCreateDatabase="CREATE TABLE student(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "gender BOOLEAN," +
                "mark REAL)";
        db.execSQL(sqlCreateDatabase);
    }

    //truy van khong tra kq
    //add
    public void addSutdent(Cong c){
        String sql = "INSERT INTO student(name,gender,mark)"+
                "VALUES(?,?,?)";
        String[] args = {c.getName(),Boolean.toString(c.isGender()),Double.toString(c.getMark())};
        SQLiteDatabase stm = getWritableDatabase();
        System.out.println(c.isGender());
        stm.execSQL(sql,args);
    }

    // UPDATE Student SET name = ?, gender = ?,mark=? WHERE id = ?
    public void updateStudent(Cong c) {
        String sql = "UPDATE student SET name = ?, gender = ?,mark=? WHERE id = ?";
        String[] args = {c.getName(),Boolean.toString(c.isGender()),Double.toString(c.getMark()),Integer.toString(c.getId())};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql,args);
    }

    //del
    public void deleteById(int id){
        String sql = "DELETE FROM student WHERE id = ?";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql, args);
    }

    //truy van tra qk
    //getAll
    public List<Cong> getAll(){
        List<Cong> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("student", null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()){
            int studentId = cursor.getInt(cursor.getColumnIndex("id"));
            String studentName = cursor.getString(cursor.getColumnIndex("name"));
            boolean studentGender = cursor.getString(cursor.getColumnIndex("gender")).equals("true");
            double studentMark=cursor.getDouble(cursor.getColumnIndex("mark"));

            list.add(new Cong(studentId,studentName,studentGender,studentMark));
        }

        return list;
    }

    public List<Cong> getByName(String name) {
        String sql = "name like ?";
        String[] args = { "%" + name + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("student", null, sql, args, null, null, null);
        List<Cong> students = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String studentName = cursor.getString(1);
            boolean gender = cursor.getInt(2) == 1;
            double mark = cursor.getDouble(3);
            Cong student = new Cong(id, studentName, gender, mark);
            students.add(student);
        }
        return students;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
