package com.example.ian.keepaccount.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.example.ian.keepaccount.R;
import com.example.ian.keepaccount.ui.activity.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //当系统到我们设定的时间点的时候会发送广播，执行这里
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Intent click = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, click, 0);

        builder.setContentTitle(context.getString(R.string.app_name))
                .setContentText("是时候记账啦！")
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent);


        notificationManager.notify(10, builder.build());

    }
}