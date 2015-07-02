package letsdecode.com.husbandtracker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MyActivity extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String SECRET_CODE = "secretKey";
    SharedPreferences sharedpreferences;
    public static String secretCodeValue;

    EditText secretCodeEdit;
    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xml object
        setContentView(R.layout.activity_my);
/*
        In order to use shared preferences , you have to call a method getSharedPreferences()
        that returns a SharedPreference instance pointing to the file that contains the values of preferences.
         */
        //first parameter is key
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //capturing EditText
        secretCodeEdit = (EditText) findViewById(R.id.secretCodeEdit);
        saveButton = (Button) findViewById(R.id.save);
        saveButton.setEnabled(false);

           /* Set Text Watcher listener */

        secretCodeEdit.addTextChangedListener(secretCodeWatcher);
        //retrieving text form edit text


    }

    /*implementing TextWatcher and OnClickListener

     */
    private final TextWatcher secretCodeWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            secretCodeEdit.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            final String secretCode = secretCodeEdit.getText().toString();
            if (secretCode.length() == 4) {
                saveButton.setEnabled(true);
                saveButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        //String secretCode = secretCodeEdit.getText().toString();
                      /*in order to save something in Editor object get an instance of Editor by calling edit()*/
                        editor.putString(SECRET_CODE, secretCode);
                        editor.apply();
                        secretCodeValue = sharedpreferences.getString(SECRET_CODE, "");
                        Log.d("code", secretCodeValue);

                        //retrieving value from Editor instance corresponding to particular key.

                    }


                });
//            secretCodeValue = sharedpreferences.getString(SECRET_CODE, "");
//            Log.d("code", secretCodeValue);


            } else {
                Toast.makeText(getApplicationContext(), "enter four digits", Toast.LENGTH_SHORT).show();
                saveButton.setEnabled(false);
            }


        }
    };

}












