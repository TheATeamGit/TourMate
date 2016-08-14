package com.example.hasibuzzaman.tourmate;

import java.util.ArrayList;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    ProfileDBSource contactDatabaseSource;
    ListView contactListView;
    ArrayList<ProfileModel>contactModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactDatabaseSource=new ProfileDBSource(this);
        contactListView= (ListView) findViewById(R.id.contactList);
        contactModels=contactDatabaseSource.getAllContact();
        ArrayAdapter<ProfileModel>contactModelArrayAdapter=new ArrayAdapter<ProfileModel>(this,android.R.layout.simple_list_item_1,contactModels);
        contactListView.setAdapter(contactModelArrayAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent singleIntent=new Intent(getApplicationContext(),ProfileInsertActivity.class);
                singleIntent.putExtra("id",contactModels.get(i).getId());
                startActivity(singleIntent);
            }
        });

    }

    public void insert(View view) {
        Intent entryIntent=new Intent(this,ProfileInsertActivity.class);
        startActivity(entryIntent);
    }
    
    public void gotoEventPage(View view){
    	Intent entrIntent=new Intent(this,EventListActivity.class);
        startActivity(entrIntent);
    }
}