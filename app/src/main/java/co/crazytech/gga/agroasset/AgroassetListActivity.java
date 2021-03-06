package co.crazytech.gga.agroasset;

import android.app.ExpandableListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import co.crazytech.gga.R;
import co.crazytech.gga.db.PersistanceManager;

/**
 * Created by eric on 7/28/2016.
 */
public class AgroassetListActivity extends FragmentActivity{
    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agroasset_listview);
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);
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
            String nickname = cursor.getString(1);
            String code = cursor.getString(2);
            String dcode = cursor.getString(3);
            Agroasset aaset = new Agroasset(id,nickname,code,dcode);
            if(cursor.getColumnIndex("maxdate")!=-1)
                Log.d("DbHarvDate",cursor.getLong(4)+"");
                aaset.setHarvDate(cursor.getString(4));
            agroassets.add(aaset);
            cursor.moveToNext();
        }
        cursor.close();
        pm.close();
        return agroassets;
    }

    public void setListAdapter(AgroassetListAdapter adapter){
        expandableListView.setAdapter(adapter);
    }

    public void setOnChildClickListener(ExpandableListView.OnChildClickListener listener){
        expandableListView.setOnChildClickListener(listener);
    }


}
