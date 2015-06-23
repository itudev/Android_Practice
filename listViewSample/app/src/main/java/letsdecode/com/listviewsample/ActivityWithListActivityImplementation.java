package letsdecode.com.listviewsample;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityWithListActivityImplementation extends ListActivity {
    ListView listView2;
    String[] dataSource = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listactivity_implementation);
        listView2 = getListView();
        // second argument: how a single row should look like.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, dataSource);
        listView2.setAdapter(adapter);

    }

    @Override
    protected void onListItemClick(android.widget.ListView l, android.view.View v, int position, long id) {
        TextView textView = (TextView) v;
//                // toast to display which view is clicked
        Toast.makeText(this, textView.getText(), Toast.LENGTH_SHORT).show();

    }
}



