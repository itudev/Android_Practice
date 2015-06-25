package letsdecode.com.spinnersample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView v = (TextView)view;
        Toast.makeText(this,v.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // get a reference of the spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.descriptions,
                android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /*1.creat the data source.
    2. define the appearance layout file through which the adapter will put data inside the spinner.
    define what to do when the user clicks on the spinner using the OnItemSelectedListener

     */


}

