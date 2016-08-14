package com.example.hasibuzzaman.tourmate;


import com.example.hasibuzzaman.tourmate.Model.EventModel;
import com.example.hasibuzzaman.tourmate.Database.EventDBSource;
import com.example.hasibuzzaman.tourmate.Database.ProfileDBSource;
import com.example.hasibuzzaman.tourmate.Model.EventModel;
import com.example.hasibuzzaman.tourmate.Model.ProfileModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EventInsertActivity extends AppCompatActivity {
	EditText profileIDEt,nameEt,placeEt,startEt,endEt,budgetEt;
    String name,place,start,end;
    int profile_id;
    double budget;
    EventDBSource eventDatabaseSource;
    EventModel eventModel;
    boolean status;
    int id;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventDatabaseSource=new EventDBSource(this);
        setContentView(R.layout.activity_event_insert);
        saveBtn= (Button) findViewById(R.id.saveBtn);
        profileIDEt=(EditText) findViewById(R.id.profile_Id_Entry);
        nameEt= (EditText) findViewById(R.id.eventNameEntry);
        placeEt= (EditText) findViewById(R.id.eventPlaceEntry);
        startEt= (EditText) findViewById(R.id.eventStartEntry);
        endEt=(EditText) findViewById(R.id.eventEndEntry);
        budgetEt= (EditText) findViewById(R.id.budgetEntry);
        id=getIntent().getIntExtra("id",0);
        if(id>0){
            eventModel=eventDatabaseSource.getContactModel(id);
            profileIDEt.setText(eventModel.getE_profile_id());
            nameEt.setText(eventModel.getE_name());
            placeEt.setText(eventModel.getE_place());
            startEt.setText(eventModel.getE_start());
            endEt.setText(eventModel.getE_end());
            String total2= new Double(eventModel.getE_budget()).toString();
            budgetEt.setText(total2);

            saveBtn.setText("update");
        }
    }

    public void save(View view) {
        name=nameEt.getText().toString().trim();
        profile_id=Integer.parseInt(profileIDEt.getText().toString());
        start=startEt.getText().toString();
        end=endEt.getText().toString();
        place=placeEt.getText().toString();
        budget=Double.parseDouble(budgetEt.getText().toString());
        eventModel=new EventModel(profile_id,name,place,start,end,budget);
        if(id>0){
            status=eventDatabaseSource.updateContact(id,eventModel);
        }else {
            status=eventDatabaseSource.addContact(eventModel);
        }
        Toast.makeText(EventInsertActivity.this,String.valueOf(status), Toast.LENGTH_SHORT).show();
        Intent homeIntent=new Intent(this,MainActivity.class);
        startActivity(homeIntent);

    }

	
}
