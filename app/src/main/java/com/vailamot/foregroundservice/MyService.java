package com.vailamot.foregroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        sendNotification();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Gửi thông báo
    private void sendNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"123");
        builder.setContentTitle("Song A");
        builder.setContentText("Mr.A");
        builder.setSmallIcon(R.drawable.ic_baseline_volume_up_24);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();

        startMusic();

        startForeground(1, notification);
    }

    // Phát nhạc
    private void startMusic() {
        if(mediaPlayer == null){
            mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.song);
        }
        mediaPlayer.start();
    }

}