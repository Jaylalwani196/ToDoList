package com.example.to_doattack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Collections.*;

public class MainActivity extends AppCompatActivity {

    TextView titlepage,subtitlepage, endpage;
    DatabaseReference reference;
    RecyclerView ourdoes;
    ArrayList<Mydoes> list;
    DoesAdapter doesAdapter;
    Button btnAddNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        endpage = findViewById(R.id.endpage);
        btnAddNew = findViewById(R.id.btnAddNew);
        //Working with data
        ourdoes = findViewById(R.id.ourdoes);
        ourdoes.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Mydoes>();



        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , NewTaskActivity.class);
                startActivity(intent);
            }
        });

        //GET DATA
        reference = FirebaseDatabase.getInstance().getReference().child("To-do Box");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           //Retrive data
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Mydoes p = dataSnapshot1.getValue(Mydoes.class);
                    list.add(p);

                }
                doesAdapter = new DoesAdapter(MainActivity.this , list);
                ourdoes.setAdapter(doesAdapter);
                doesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext() , " No Data" , Toast.LENGTH_SHORT).show();

            }
        });

       Collections.sort(list, new Comparator<Mydoes>() {
           @Override
           public int compare(Mydoes o1, Mydoes o2) {
               return o1.getTitledoes().compareToIgnoreCase(o2.getTitledoes());
           }
       });

    }

}
