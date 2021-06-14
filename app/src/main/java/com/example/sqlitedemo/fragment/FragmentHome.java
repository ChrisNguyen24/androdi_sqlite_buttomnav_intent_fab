package com.example.sqlitedemo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.RevAdapter;
import com.example.sqlitedemo.SQLiteStudentHelper;
import com.example.sqlitedemo.model.Cong;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment {
    private SearchView searchView;
    private RevAdapter revAdapter;
    private RecyclerView recyclerView;
    private List<Cong> list;
    private EditText etSearch;
    //sqli
    private SQLiteStudentHelper sqli;

    private Button bta;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.rev);
        revAdapter = new RevAdapter(getActivity());
//        list  = new ArrayList<>();
//        list.add(new Cong(1,"Cong",true,7));
//        sqli = new SQLiteStudentHelper(getContext());
        sqli = new SQLiteStudentHelper(getContext());
        revAdapter.setData(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(revAdapter);
        bta = v.findViewById(R.id.bta);

        searchView = v.findViewById(R.id.serView);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Cong> ls = new ArrayList<>();
                ls = sqli.getByName(String.valueOf(newText));
                revAdapter.setData(ls);
                return false;
            }
        });
        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "loading...", Toast.LENGTH_SHORT).show();
                list = sqli.getAll();
                revAdapter.setData(list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(revAdapter);
//                revAdapter.addData(new Cong(1, "han", true, 9));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "loading.....", Toast.LENGTH_SHORT).show();
        list = sqli.getAll();
        revAdapter.setData(list);
    }
}