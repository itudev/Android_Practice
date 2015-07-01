package letsdecode.com.husbandtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SmsReceiver extends BroadcastReceiver {
    LocationManager mLocationManager;
    long LOCATION_REFRESH_TIME;
    float LOCATION_REFRESH_DISTANCE;
    SmsMessage[] message = null;
    String phoneNumber;
    String messageBody;
    double latitude;
    double longitude;
    String sendThisMessage;
    Geocoder geocoder;
    List<Address> addressList = new ArrayList<Address>();

    @Override
    public void onReceive(Context context, Intent intent) {
        //get the sms message passed in
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Object[] sms = (Object[]) bundle.get("pdus");
            message = new SmsMessage[sms.length];
            for (int i = 0; i < message.length; i++) {
                message[i] = SmsMessage.createFromPdu((byte[]) sms[i]);
                if (i == 0) {
                    //get the sender's phonenumber
                    phoneNumber = message[i].getOriginatingAddress();
                }
                //messageBody
                messageBody = message[i].getMessageBody().toString();
            }
            geocoder = new Geocoder(context, Locale.getDefault());
            if (messageBody.equals(MyActivity.secretCodeValue)) {
                mLocationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                        LOCATION_REFRESH_DISTANCE, mLocationListener);
            } else {
                return;
            }
            //so that other applications not handle this 
            this.abortBroadcast();

        }
    }

    final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            //single call to update location
            mLocationManager.removeUpdates(mLocationListener);

            try {
                addressList = geocoder.getFromLocation(
                        latitude, longitude, 1);
                if (addressList != null && addressList.size() > 0) {
                    Address address = addressList.get(0);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                        sb.append(address.getAddressLine(i)).append("\n");
                    }
                    sb.append(address.getLocality()).append("\n");
                    sb.append(address.getPostalCode()).append("\n");
                    sb.append(address.getCountryName());
                    sendThisMessage = sb.toString();
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, sendThisMessage, null, null);
                }
            } catch (IOException e) {

            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Latitude", "disable");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Latitude", "enable");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("Latitude", "status");
        }
    };


}


