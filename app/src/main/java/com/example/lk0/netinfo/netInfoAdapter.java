package com.example.lk0.netinfo;


import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class netInfoAdapter extends ArrayAdapter<appNetInfo> {
    private final Context context;
    private final ArrayList<appNetInfo> values;

    public netInfoAdapter(Context context, ArrayList<appNetInfo> values) {
        super(context, R.layout.activity_main, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_main, parent, false);
        TextView appInfo = (TextView) rowView.findViewById(R.id.appInfo);
        ImageView appIcon = (ImageView) rowView.findViewById(R.id.appIcon);
        String appName = values.get(position).getName();
        appInfo.setText(values.get(position).toString());
        if (values.get(position).getUID() == -1) {
            appIcon.setVisibility(View.GONE);
            return rowView;
        }
        Drawable icon = null;
        if (appName.contains(":")) {
            appName = appName.substring(0,appName.indexOf(":"));
        }
        try {
            icon = context.getPackageManager().getApplicationIcon(appName);
            appIcon.setBackground(icon);
        } catch (PackageManager.NameNotFoundException e){
            appIcon.setBackground(context.getPackageManager().getDefaultActivityIcon());
            e.printStackTrace();
        }
        return rowView;

    }

}
