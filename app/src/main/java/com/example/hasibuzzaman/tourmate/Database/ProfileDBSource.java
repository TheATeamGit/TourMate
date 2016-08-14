package com.example.hasibuzzaman.tourmate.Database;

import java.util.ArrayList;

import com.example.hasibuzzaman.tourmate.Model.EventModel;
import com.example.hasibuzzaman.tourmate.Database.EventDBSource;
import com.example.hasibuzzaman.tourmate.Database.ProfileDBSource;
import com.example.hasibuzzaman.tourmate.Model.EventModel;
import com.example.hasibuzzaman.tourmate.Model.ProfileModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProfileDBSource {
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    ProfileModel contactModel;

    public ProfileDBSource(Context context) {
        databaseHelper=new DatabaseHelper(context);
    }


    public void open(){
        database=databaseHelper.getWritableDatabase();
    }
    public void close(){
        databaseHelper.close();
    }

    public boolean addContact(ProfileModel profileModel){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME,profileModel.getName());
        contentValues.put(DatabaseHelper.COL_EMAIL, profileModel.getEmail());
        contentValues.put(DatabaseHelper.COL_PASSWORD, profileModel.getPassword());
        contentValues.put(DatabaseHelper.COL_IMAGEPATH, profileModel.getImagePath());
        contentValues.put(DatabaseHelper.COL_PHONE,profileModel.getPhoneNumber());
        contentValues.put(DatabaseHelper.COL_EMERGENCY, profileModel.getEmergencyNo());


        long inserted=database.insert(DatabaseHelper.TABLE_PROFILE,null,contentValues);

        this.close();
        if(inserted>0){
            return true;
        }else {
            return false;
        }

    }

    public ProfileModel getContactModel(int id){
        this.open();

        Cursor cursor=database.query(DatabaseHelper.TABLE_PROFILE,new String[]{DatabaseHelper.COL_ID,DatabaseHelper.COL_NAME,DatabaseHelper.COL_EMAIL,DatabaseHelper.COL_PASSWORD,DatabaseHelper.COL_IMAGEPATH,DatabaseHelper.COL_PHONE,DatabaseHelper.COL_EMERGENCY},DatabaseHelper.COL_ID+" = "+id,null,null,null,null);

        cursor.moveToFirst();

        int pId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String pName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
        String pEmail=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL));
        String pPassword=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PASSWORD));
        String pImagePath=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_IMAGEPATH));
        String pPhone=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));
        String pEmergency=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMERGENCY));

        cursor.close();
        this.close();
        contactModel=new ProfileModel(pId,pName,pEmail,pPassword,pImagePath,pPhone,pEmergency);
        return contactModel;
    }

    public ArrayList<ProfileModel>getAllContact(){
        ArrayList<ProfileModel>contactModels=new ArrayList<ProfileModel>();
        this.open();

        Cursor cursor=database.rawQuery("select * from "+DatabaseHelper.TABLE_PROFILE,null);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                int pId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String pName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                String pEmail=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL));
                String pPassword=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PASSWORD));
                String pImagePath=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_IMAGEPATH));
                String pPhone=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));
                String pEmergency=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMERGENCY));
                contactModel=new ProfileModel(pId,pName,pEmail,pPassword,pImagePath,pPhone,pEmergency);
                cursor.moveToNext();
                contactModels.add(contactModel);
            }
        }
        cursor.close();
        this.close();
        return contactModels;
    }


    public boolean updateContact(int id,ProfileModel contactModel){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME,contactModel.getName());
        contentValues.put(DatabaseHelper.COL_EMAIL, contactModel.getEmail());
        contentValues.put(DatabaseHelper.COL_PASSWORD, contactModel.getPassword());
        contentValues.put(DatabaseHelper.COL_IMAGEPATH, contactModel.getImagePath());
        contentValues.put(DatabaseHelper.COL_PHONE,contactModel.getPhoneNumber());
        contentValues.put(DatabaseHelper.COL_EMERGENCY, contactModel.getEmergencyNo());

        int updated=database.update(DatabaseHelper.TABLE_PROFILE,contentValues,DatabaseHelper.COL_ID+" = "+id,null);
        this.close();

        if(updated>0){
            return true;
        }else{
            return false;
        }

    }

    public boolean deleteContact(int id){
        this.open();

        int deleted=database.delete(DatabaseHelper.TABLE_PROFILE,DatabaseHelper.COL_ID+" = "+id,null);

        this.close();
        if(deleted>0){
            return true;
        }
        else {
            return false;
        }
    }


}
