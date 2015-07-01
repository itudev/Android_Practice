package letsdecode.com.husbandtracker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;


public class MyActivity extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String SECRET_CODE = "secretKey";
    SharedPreferences sharedpreferences;
    public static String secretCodeValue;

    EditText secretCodeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xml object
        setContentView(R.layout.activity_my);
        //capturing EditText
        secretCodeEdit = (EditText) findViewById(R.id.secretCodeEdit);
        //retreiving text form edit text
        String secretCode = secretCodeEdit.getText().toString();
        /*
        In order to use shared preferences , you have to call a method getSharedPreferences()
        that returns a SharedPreference instance pointing to the file that contains the values of preferences.
         */
        //first parameter is key
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        /*in order to save something in Editor object get an instance of Editor by calling edit().

         */
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SECRET_CODE, secretCode);
        editor.apply();
        //retrieving value from Editor instance corresponding to particular key.
        secretCodeValue = sharedpreferences.getString(SECRET_CODE, "");


    }


}