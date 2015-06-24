package letsdecode.com.spinnersample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MyActivity extends Activity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // get a reference of the spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.descriptions, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }

    /*1.creat the data source.
    2. define the appearance layout file through which the adapter will put data inside the spinner.
    define what to do when the user clicks on the spinner using the OnItemSelectedListener

     */


}

