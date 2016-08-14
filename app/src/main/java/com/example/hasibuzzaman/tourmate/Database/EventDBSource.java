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

public class EventDBSource {
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    EventModel contactModel;

    public EventDBSource(Context context) {
        databaseHelper=new DatabaseHelper(context);
    }


    public void open(){
        database=databaseHelper.getWritableDatabase();
    }
    public void close(){
        databaseHelper.close();
    }

    public boolean addContact(EventModel contactModel){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_E_PFOFILE_ID,contactModel.getE_profile_id());
        contentValues.put(DatabaseHelper.COL_E_NAME,contactModel.getE_name());
        contentValues.put(DatabaseHelper.COL_E_PLACE,contactModel.getE_place());
        contentValues.put(DatabaseHelper.COL_E_START,contactModel.getE_start());
        contentValues.put(DatabaseHelper.COL_E_END,contactModel.getE_end());
        contentValues.put(DatabaseHelper.COL_E_BUDGET,contactModel.getE_budget());


        long inserted=database.insert(DatabaseHelper.TABLE_EVENT,null,contentValues);

        this.close();
        if(inserted>0){
            return true;
        }else {
            return false;
        }

    }

    public EventModel getContactModel(int id){
        this.open();

        Cursor cursor=database.query(DatabaseHelper.TABLE_EVENT,new String[]{DatabaseHelper.COL_E_ID,DatabaseHelper.COL_E_PFOFILE_ID,DatabaseHelper.COL_E_NAME,DatabaseHelper.COL_E_PLACE,DatabaseHelper.COL_E_START,DatabaseHelper.COL_E_END,DatabaseHelper.COL_E_BUDGET},DatabaseHelper.COL_E_ID+" = "+id,null,null,null,null);
        
        cursor.moveToFirst();

        int mId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_E_ID));
        int mProfileId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_E_PFOFILE_ID));
        String mName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_NAME));
        String mPlace=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_PLACE));
        String mStart=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_START));
        String mEnd=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_END));
        String mBudget=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_BUDGET));
        

        cursor.close();
        this.close();
        contactModel=new EventModel(mId,mProfileId,mName,mPlace,mStart,mEnd,Double.parseDouble(mBudget));
        return contactModel;
    }

    public ArrayList<EventModel>getAllContact(){
        ArrayList<EventModel>contactModels=new ArrayList<EventModel>();
        this.open();

        Cursor cursor=database.rawQuery("select * from "+DatabaseHelper.TABLE_EVENT,null);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
            	int mId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_E_ID));
                int mProfileId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_E_PFOFILE_ID));
                String mName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_NAME));
                String mPlace=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_PLACE));
                String mStart=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_START));
                String mEnd=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_END));
                String mBudget=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_E_BUDGET));
                contactModel=new EventModel(mId,mProfileId,mName,mPlace,mStart,mEnd,Double.parseDouble(mBudget));
                
                cursor.moveToNext();
                contactModels.add(contactModel);
            }
        }
        cursor.close();
        this.close();
        return contactModels;
    }


    public boolean updateContact(int id,EventModel contactModel){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_E_PFOFILE_ID,contactModel.getE_profile_id());
        contentValues.put(DatabaseHelper.COL_E_NAME,contactModel.getE_name());
        contentValues.put(DatabaseHelper.COL_E_PLACE,contactModel.getE_place());
        contentValues.put(DatabaseHelper.COL_E_START,contactModel.getE_start());
        contentValues.put(DatabaseHelper.COL_E_END,contactModel.getE_end());
        contentValues.put(DatabaseHelper.COL_E_BUDGET,contactModel.getE_budget());

        int updated=database.update(DatabaseHelper.TABLE_EVENT,contentValues,DatabaseHelper.COL_E_ID+" = "+id,null);
        this.close();

        if(updated>0){
            return true;
        }else{
            return false;
        }

    }

    public boolean deleteContact(int id){
        this.open();

        int deleted=database.delete(DatabaseHelper.TABLE_EVENT,DatabaseHelper.COL_E_ID+" = "+id,null);

        this.close();
        if(deleted>0){
            return true;
        }
        else {
            return false;
        }
    }


}
