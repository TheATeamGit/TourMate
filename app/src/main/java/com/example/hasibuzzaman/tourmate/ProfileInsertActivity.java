package com.example.hasibuzzaman.tourmate;



import com.example.hasibuzzaman.tourmate.Database.ProfileDBSource;

import com.example.hasibuzzaman.tourmate.Model.ProfileModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileInsertActivity extends Activity {
    EditText nameEt,emailEt,passwordEt,imagePathEt,emergencyEt,phoneEt;
    String name,email,password,imagepath;
    String phone,emergency;
    ProfileDBSource profileDBSource;
    ProfileModel contactModel;
    boolean status;
    int id;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileDBSource=new ProfileDBSource(this);
        setContentView(R.layout.activity_profile_insert);
        saveBtn= (Button) findViewById(R.id.saveBtn);
        nameEt= (EditText) findViewById(R.id.nameEntry);
        emailEt=(EditText) findViewById(R.id.emailEntry);
        passwordEt=(EditText) findViewById(R.id.passwordEntry);
        imagePathEt=(EditText) findViewById(R.id.imageEntry);
        phoneEt= (EditText) findViewById(R.id.phoneEntry);
        emergencyEt=(EditText) findViewById(R.id.emergencyEntry);
        id=getIntent().getIntExtra("id",0);
        if(id>0){
            contactModel=profileDBSource.getContactModel(id);
            nameEt.setText(contactModel.getName());
            emailEt.setText(contactModel.getEmail());
            passwordEt.setText(contactModel.getPassword());
            imagePathEt.setText(contactModel.getImagePath());
            phoneEt.setText(contactModel.getPhoneNumber());
            emergencyEt.setText(contactModel.getEmergencyNo());
            saveBtn.setText("Update");
            setTitle("Update Profile");
        }
    }

    public void save(View view) {
        name=nameEt.getText().toString().trim();
        if(name.length()<=0){
            //nameEt.setError("data not found");
        }
        email=emailEt.getText().toString();
        password=passwordEt.getText().toString();
        imagepath=imagePathEt.getText().toString();
        phone=phoneEt.getText().toString();
        emergency=emergencyEt.getText().toString();
        
        contactModel=new ProfileModel(name,email,password,imagepath,phone,emergency);
        if(id>0){
            status=profileDBSource.updateContact(id,contactModel);
        }else {
            status=profileDBSource.addContact(contactModel);
        }
        Toast.makeText(ProfileInsertActivity.this,String.valueOf(status), Toast.LENGTH_SHORT).show();
        Intent homeIntent=new Intent(this,MainActivity.class);
        startActivity(homeIntent);

    }
}