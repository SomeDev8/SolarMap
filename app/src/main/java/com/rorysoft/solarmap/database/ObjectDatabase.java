package com.rorysoft.solarmap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rorysoft.solarmap.Constants;

public class ObjectDatabase extends SQLiteOpenHelper {

    public static final String TAG = ObjectDatabase.class.getName();
    static final int DB_VERSION = 1;

    private static ObjectDatabase mInstance = null;

    // This method will ensure the same instance of SQLiteOpenHelper is
    // accessed across the application, preventing database leakage

    public static ObjectDatabase getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new ObjectDatabase(context.getApplicationContext());
        }
        return mInstance;
    }

    // The SQL commands are stored in Strings as values

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Constants.DB_TABLE + "("
            + Constants._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constants.COLUMN_NAME + " TEXT NOT NULL, "
            + Constants.COLUMN_DIAMETER + " TEXT NOT NULL, "
            + Constants.COLUMN_MASS + " TEXT NOT NULL, "
            + Constants.COLUMN_DIST_FROM_SUN + " TEXT, "
            + Constants.COLUMN_DESC + " TEXT NOT NULL)";


    public ObjectDatabase(Context context) {
        super(context, Constants.DB_NAME, null, DB_VERSION);
    }

    // The database is created with a try catch block, prepared for unlikely SQL exception

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Log.d(TAG, "Table created!");
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.d(TAG, "Error creating table");
        }
    }

    // When the application is updated, the current table in the database is cleared and a new one is created
    // if we want to any values

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        clearTable(db);
//        onCreate(db);
    }

    private void clearTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.DB_TABLE);
    }
}





