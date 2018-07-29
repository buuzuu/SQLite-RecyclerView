package com.example.hritik.sqlite_with_recycler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    private Context context;


    public DataBaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.key_id + " INTEGER PRIMARY KEY," + Constants.key_name + " TEXT," + Constants.key_religion + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);

    }

    // add single bioData to contact


    public void addBioData(BioData bioData) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.key_name, bioData.getName());
        values.put(Constants.key_religion, bioData.getReligion());
        long id = database.insert(Constants.TABLE_NAME, null, values);
        if (id < 0) {
            Toast.makeText(context, "Insertion Unsuccessful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Insertion Unsuccessful", Toast.LENGTH_SHORT).show();
        }
        database.close();
    }

    public List<BioData> getAllBiodata() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(" SELECT * FROM " + Constants.TABLE_NAME, null);

        List<BioData> bioDataList = new ArrayList<>();

//        if (cursor != null)
//            cursor.moveToFirst();

        /*do {
            BioData bioData1=new BioData();
         //   bioData1.setId(Integer.parseInt(cursor.getString(0)));
            bioData1.setId(Integer.parseInt(String.valueOf(cursor.getString(0))));
            bioData1.setName(cursor.getString(1));
            bioData1.setReligion(cursor.getString(2));
            bioDataList.add(bioData1);

            }while (cursor.moveToNext());*/

        while (cursor.moveToNext()) {
            BioData bioData1 = new BioData();
            //   bioData1.setId(Integer.parseInt(cursor.getString(0)));
            bioData1.setId(Integer.parseInt(String.valueOf(cursor.getString(0))));
            bioData1.setName(cursor.getString(1));
            bioData1.setReligion(cursor.getString(2));
            bioDataList.add(bioData1);


        }

        return bioDataList;


    }


    public int BioDataLength() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(" SELECT * FROM " + Constants.TABLE_NAME, null);

        return cursor.getCount();


    }


    public BioData getBioData(int id) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(Constants.TABLE_NAME, new String[]{Constants.key_id, Constants.key_name, Constants.key_religion}
                , Constants.key_id + " =? ", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        BioData bioData = new BioData();
        bioData.setId(Integer.parseInt(cursor.getString(0)));
        bioData.setName(cursor.getString(1));
        bioData.setReligion(cursor.getString(2));
        return bioData;

    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(Constants.TABLE_NAME, Constants.key_id + " =?", new String[]{String.valueOf(id)});
    }

    public int updateBioData(BioData bioData, int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.key_name, bioData.getName());
        values.put(Constants.key_religion, bioData.getReligion());
        return database.update(Constants.TABLE_NAME, values, Constants.key_id + "=?", new String[]{String.valueOf(id)});

    }

}
