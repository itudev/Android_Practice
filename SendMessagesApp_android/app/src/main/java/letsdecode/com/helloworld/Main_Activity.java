package letsdecode.com.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class Main_Activity extends Activity implements View.OnClickListener {
    private static final String TAG = "activityCycle1";
    Button but;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.i(TAG, getClass().getSimpleName() + " onCreate() called");
        //capturing the button
        but = (Button) findViewById(R.id.button1);

        but.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //passing implicit intent, to open gallery with images and asking to get the result back from the gallery app
        startActivityForResult(intent, 1);
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //matching the request code
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            // if data is not equal to null, set another implicit intent to send the data
            Intent picMessageIntent = new Intent(android.content.Intent.ACTION_SEND);
            picMessageIntent.setType("image/jpeg");
            String str = String.valueOf(data.getData());
            picMessageIntent.putExtra(Intent.EXTRA_STREAM, data.getData());
            Intent chooser;
            chooser = Intent.createChooser(picMessageIntent, "send images");
            startActivity(chooser);
        }
    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i(TAG, getClass().getSimpleName() + " onStart() called");
//    }
//
//    to know how many times has the user view the app
    // we will put the counter and increase it in the method resume.
    // and in order to save the data even while app get paused or orientation changes.


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, getClass().getSimpleName() + " onResume() called");
        counter++;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counter);
        Log.i(TAG, getClass().getSimpleName() + "onSaveInstanceState() is called and current counter value is" + counter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt("counter");
        Log.i(TAG, getClass().getSimpleName() + "onRestoreInstanceState() is called and current counter value is" + counter);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, getClass().getSimpleName() + " onPause() called");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, getClass().getSimpleName() + " onRestart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, getClass().getSimpleName() + " onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, getClass().getSimpleName() + " onDestroy() called");
    }


//    public void onClickButton(View view) {
//        Intent intent = new Intent(this, MyActivity2.class);
//        startActivity(intent);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

}
