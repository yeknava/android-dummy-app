package com2.example.newboston.newboston;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by nava on 8/19/14.
 */
public class HotOrNot {
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_HOTNESS = "persons_hotness";

    private static final String DATABASE_NAME = "HotOrNotdb";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final Integer DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDataBase;

    public String getData() {
        String[] columns = new String[] { KEY_ID, KEY_NAME, KEY_HOTNESS };
        Cursor cursor = ourDataBase.query(DATABASE_TABLE, columns, null, null,null, null, null );
        String result = "";
        int iRow = cursor.getColumnIndex(KEY_ID);
        int iName = cursor.getColumnIndex(KEY_NAME);
        int iHotness = cursor.getColumnIndex(KEY_HOTNESS);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result = result +
                    cursor.getString(iRow) + " " +
                    cursor.getString(iName) + "\t" + "\t" + "\t" +
                    cursor.getString(iHotness) + "\n" ;
        }
        return result;
    }

    public String getName(long rowId) throws SQLException {
        String[] columns = new String[] {KEY_ID, KEY_NAME, KEY_HOTNESS};
        Cursor cursor = ourDataBase.query(DATABASE_TABLE, columns, KEY_ID+ "="+ rowId, null, null, null, null );
        if (cursor!= null) {
            cursor.moveToFirst();
            String name = cursor.getString(1);
            return name;
        }
        return null;
    }

    public String getHotness(long rowId) throws SQLException {
        String[] columns = new String[] {KEY_ID, KEY_NAME, KEY_HOTNESS};
        Cursor cursor = ourDataBase.query(DATABASE_TABLE, columns, KEY_ID+ "="+ rowId, null, null, null, null );
        if (cursor!= null) {
            cursor.moveToFirst();
            String hotness = cursor.getString(2);//in 2 shomare columns, id -> 0, name-> 1, hotness->2
            return hotness;
        }
        return null;
    }

    public void updateEntry(long rowId, String name, String hotness) throws SQLException {
        ContentValues contentValuesUpdate = new ContentValues();
        contentValuesUpdate.put(KEY_NAME, name);
        contentValuesUpdate.put(KEY_HOTNESS, hotness);
        ourDataBase.update(DATABASE_TABLE, contentValuesUpdate, KEY_ID+ "=" +rowId, null);
    }

    public void deleteEntry(long rowId) throws SQLException {
        ourDataBase.delete(DATABASE_TABLE, KEY_ID+ "=" +rowId, null);
    }


    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }



        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE + " " + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_HOTNESS + " TEXT NOT NULL);"

            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }
    public HotOrNot(Context ourContext) {
        this.ourContext = ourContext;
    }
    public HotOrNot open() throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDataBase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        ourHelper.close();
    }
    public long createentry(String name, String hotness) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_HOTNESS, hotness);
        return ourDataBase.insert(DATABASE_TABLE, null, contentValues);
    }
}
