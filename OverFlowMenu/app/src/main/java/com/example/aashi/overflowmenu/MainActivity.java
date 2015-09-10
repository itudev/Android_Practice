package com.example.aashi.overflowmenu;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;






import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayToast("entered onCreate");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        displayToast("entered onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        displayToast("onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (item.getItemId() == R.id.menu_one) {
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }

            displayToast("first item is selected");
            return true;

        }

        if (item.getItemId() == R.id.menu_two) {
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }

            displayToast("second item is selected");
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
}

//    public class MainActivity extends Activity {
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//
//            ActionBar mActionBar = getActionBar();
//            mActionBar.setDisplayShowHomeEnabled(false);
//            mActionBar.setDisplayShowTitleEnabled(false);
//            LayoutInflater mInflater = LayoutInflater.from(this);
//
//            View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
//            TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
//            mTitleTextView.setText("My Own Title");
//
//            ImageButton imageButton = (ImageButton) mCustomView
//                    .findViewById(R.id.imageButton);
//            imageButton.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getApplicationContext(), "Refresh Clicked!",
//                            Toast.LENGTH_LONG).show();
//                }
//            });
//
//            mActionBar.setCustomView(mCustomView);
//            mActionBar.setDisplayShowCustomEnabled(true);
//        }
//
//    }



