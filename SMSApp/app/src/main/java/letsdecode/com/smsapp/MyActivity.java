package letsdecode.com.smsapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {
    Button smsButton;
    Button contacts;
    final int PICK_CONTACTS = 1;
    EditText phoneEdit, messageEdit;
    TextView enterPhone, enterMessage;
    String SENT = "SMS SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        smsButton = (Button) findViewById(R.id.smsButton);
        phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        messageEdit = (EditText) findViewById(R.id.messageEdit);
        enterPhone = (TextView) findViewById(R.id.enterPhone);
        enterMessage = (TextView) findViewById(R.id.enterMessage);
        contacts = (Button) findViewById(R.id.contacts);
        /*context, result code, intent object, flag?

        two pending intent objects, they will be used to send broadcast later
         */
        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);


        smsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sendSMS();

            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACTS);

            }
        });


    }


    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        String phone;
        String email;
        String name;
        Cursor cursor;  // Cursor object
        String mime;    // MIME type
        int dataIdx;    // Index of DATA1 column
        int mimeIdx;    // Index of MIMETYPE column
        int nameIdx;    // Index of DISPLAY_NAME column

        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACTS):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    // Get the name
                    cursor = getContentResolver().query(contactData,
                            new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                            null, null, null);
                    if (cursor.moveToFirst()) {
                        nameIdx = cursor.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME);
                        name = cursor.getString(nameIdx);

                        // Set up the projection
                        String[] projection = {
                                ContactsContract.Data.DISPLAY_NAME,
                                ContactsContract.Contacts.Data.DATA1,
                                ContactsContract.Contacts.Data.MIMETYPE};

                        // Query ContactsContract.Data
                        cursor = getContentResolver().query(
                                ContactsContract.Data.CONTENT_URI, projection,
                                ContactsContract.Data.DISPLAY_NAME + " = ?",
                                new String[]{name},
                                null);

                        if (cursor.moveToFirst()) {
                            // Get the indexes of the MIME type and data
                            mimeIdx = cursor.getColumnIndex(
                                    ContactsContract.Contacts.Data.MIMETYPE);
                            dataIdx = cursor.getColumnIndex(
                                    ContactsContract.Contacts.Data.DATA1);

                            // Match the data to the MIME type, store in variables
                            do {
                                mime = cursor.getString(mimeIdx);
                                if (ContactsContract.CommonDataKinds.Email
                                        .CONTENT_ITEM_TYPE.equalsIgnoreCase(mime)) {
                                    email = cursor.getString(dataIdx);
                                }
                                if (ContactsContract.CommonDataKinds.Phone
                                        .CONTENT_ITEM_TYPE.equalsIgnoreCase(mime)) {
                                    phone = cursor.getString(dataIdx);
                                    phoneEdit.setText(phone);
                                }
                            } while (cursor.moveToNext());
                        }
                    }
                }
        }
    }


    private void sendSMS() {
        SmsManager sms = SmsManager.getDefault();
        //get text from edittext
        String phoneNumber = phoneEdit.getText().toString();
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "please enter number", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = messageEdit.getText().toString();
        if (message.isEmpty()) {

            Toast.makeText(this, "please enter message", Toast.LENGTH_SHORT).show();
            return;
        }
        /*sendTextMessage takes 5 arguments
        destinationAddress
        sc Address
        text
        sentIntent(pending intent to invoke when the message is sent)
        deliveryIntent(Pending intent to invoke when the message has been delivered)
         */


        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //create the Broadcast receiver when SMS is sent
        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent", Toast.LENGTH_SHORT).show();

                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No Service", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic Failure", Toast.LENGTH_SHORT).show();


                }
            }
        };
//create the broadcast receiver when sms is delivered
        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS Delivered", Toast.LENGTH_SHORT).show();

                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not Delivered", Toast.LENGTH_SHORT).show();

                        break;


                }
            }
        };


        registerReceiver(smsSentReceiver, new IntentFilter(SENT));
        registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
