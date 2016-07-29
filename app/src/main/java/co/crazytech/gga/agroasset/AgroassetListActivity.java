package co.crazytech.gga.agroasset;

import android.app.ExpandableListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import co.crazytech.gga.db.PersistanceManager;

/**
 * Created by eric on 7/28/2016.
 */
public class AgroassetListActivity extends ExpandableListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected List<Agroasset> agroassets(String sql) {
        List<Agroasset> agroassets = new ArrayList<Agroasset>();
        PersistanceManager pm = new PersistanceManager(this);
        pm.open();
        SQLiteDatabase db = pm.getDb();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Long id = cursor.getLong(0);
            int geoId = cursor.getInt(1);
            String nickname = cursor.getString(2);
            agroassets.add(new Agroasset(id,geoId,nickname));
            cursor.moveToNext();
        }
        cursor.close();
        pm.close();
        return agroassets;
    }
}