package com.example.to_doattack;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class NewTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextView titlepage, addtitle, titledoes;
    TextView addDesc, titleDesc, addTarget, titleTarget;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt(); //For id


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        titledoes = findViewById(R.id.titleAdd);
        addtitle = findViewById(R.id.addTitle);
        titlepage = findViewById(R.id.titlepage);
        addDesc = findViewById(R.id.addDesc);
        titleDesc = findViewById(R.id.titleDesc);
        addTarget = findViewById(R.id.addTarget);
        titleTarget = findViewById(R.id.titleTarget);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);


        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //INSERT DATA
                reference = FirebaseDatabase.getInstance().getReference().child("To-do Box").child("does" + doesNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titledoes").setValue(titledoes.getText().toString());
                        dataSnapshot.getRef().child("descdoes").setValue(titleDesc.getText().toString());
                        dataSnapshot.getRef().child("datedoes").setValue(titleTarget.getText().toString());
                        dataSnapshot.getRef().child("id").setValue(doesNum.toString());


                        Intent intent = new Intent(NewTaskActivity.this, MainActivity.class);
                        startActivity(intent);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewTaskActivity.this, MainActivity.class));
            }
        });


        titleTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickupFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");


            }
        });


    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR , year);
        c.set(Calendar.MONTH , month);
        c.set(Calendar.DAY_OF_MONTH , dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        titleTarget.setText(currentDateString);
    }
}
