package letsdecode.com.listviewimage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MyActivity extends Activity {
    ListView listView;
    String[] data = {"SUNDAY", "MONDAY", "TUESDAY","WEDNESDAY","THURSDAY", "FRIDAY", "SATURDAY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this, R.layout.single_row,
        R.id.textView, data);
        listView.setAdapter(adapter);

    }



}
