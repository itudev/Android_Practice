package letsdecode.com.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MyActivity2 extends Activity  {
    Button button;

    private static final String TAG = "activityCycle2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName() + " onCreate() called");
        setContentView(R.layout.activity_my_activity2);
        button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new ButtonHandler());
    }

    //inner class
    class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Log.d("jaj", "button3 clicked");

            Intent intent = new Intent(MyActivity2.this, MyActivity3.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, getClass().getSimpleName() + " onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, getClass().getSimpleName() +" onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,getClass().getSimpleName() + " onPause() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,getClass().getSimpleName() + " onRestart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, getClass().getSimpleName() +" onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, getClass().getSimpleName() +" onDestroy() called");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
