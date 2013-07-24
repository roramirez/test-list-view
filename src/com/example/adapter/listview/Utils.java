package com.example.adapter.listview;

import java.util.Locale;
import java.util.UUID;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Utils
 * 
 * @author Rodrigo Pérez <rp@qmd.cl>
 * 
 */
public class Utils {
    
    /**
     * Get unique device id
     * 
     * @param context
     *            {@link Context}
     * 
     * @return Unique device id
     * 
     */
    public static String getDeviceId(Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = ""
                + android.provider.Settings.Secure.getString(
                        context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        return deviceId;
    }
    

    /**
     * Get user agent
     * 
     * @return User Agent
     */
    public static String getUserAgent(Context context) {
        StringBuffer lang = new StringBuffer();
        String userAgent;
        
        String appVersion = "";
        final Locale l = Locale.getDefault();
        final String language = l.getLanguage();
        if(language != null) {
            lang.append(language.toLowerCase());
            final String country = l.getCountry();
            if (country != null) {
                lang.append("-");
                lang.append(country.toLowerCase());
            }
        }
        else
        {
        	lang.append("en");
        }
        try {
            appVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            // e.printStackTrace();
        	appVersion = "unknown";
        }          
        final String base = "Luqea/%s (Linux; U; Android %s; %s; %s Build/%s)";
        userAgent = String.format(base, appVersion, Build.VERSION.RELEASE, lang, Build.MODEL, Build.ID);
        return userAgent;

    }
    
    // based on http://stackoverflow.com/a/3495908
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter(); 
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


}