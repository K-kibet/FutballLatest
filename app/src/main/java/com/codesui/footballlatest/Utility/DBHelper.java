package com.codesui.footballlatest.Utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "MyDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE strings_table (id INTEGER PRIMARY KEY, string_value INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS strings_table");
        onCreate(db);
    }

    public void insertStrings(Integer[] strings) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Integer str : strings) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("string_value", str);
            db.insert("strings_table", null, contentValues);
        }
    }

    public List<Integer> getStrings() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM strings_table", null);
        List<Integer> stringList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                stringList.add(cursor.getInt(1)); // column 1 is string_value
            } while (cursor.moveToNext());
        }
        cursor.close();
        return stringList;
    }
}
