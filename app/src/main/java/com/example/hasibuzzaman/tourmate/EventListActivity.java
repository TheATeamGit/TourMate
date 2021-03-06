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
import android.widget.Toast;

public class EventListActivity extends AppCompatActivity {
	EventDBSource contactDatabaseSource;
    ListView contactListView;
    ArrayList<EventModel>contactModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        contactDatabaseSource=new EventDBSource(this);
        contactListView= (ListView) findViewById(R.id.contactList);
        contactModels=contactDatabaseSource.getAllContact();
        ArrayAdapter<EventModel>contactModelArrayAdapter=new ArrayAdapter<EventModel>(this,android.R.layout.simple_list_item_1,contactModels);
        contactListView.setAdapter(contactModelArrayAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent singleIntent=new Intent(getApplicationContext(),SingleDetailsActivity.class);
                //singleIntent.putExtra("id",contactModels.get(i).getE_id());
                Toast.makeText(EventListActivity.this, "Passing : "+contactModels.get(i).getE_id(), Toast.LENGTH_LONG).show();
                //startActivity(singleIntent);
            }
        });

    }

    public void insert(View view) {
        Intent entryIntent=new Intent(this,EventInsertActivity.class);
        startActivity(entryIntent);
    }
}
