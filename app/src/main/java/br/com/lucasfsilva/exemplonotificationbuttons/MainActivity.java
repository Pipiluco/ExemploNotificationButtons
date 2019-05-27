package br.com.lucasfsilva.exemplonotificationbuttons;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static br.com.lucasfsilva.exemplonotificationbuttons.App.CHANNEL_ID_01;
import static br.com.lucasfsilva.exemplonotificationbuttons.App.CHANNEL_ID_02;


public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    private EditText edtTitulo, edtMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtMensagem = (EditText) findViewById(R.id.edtMensagem);

    }

    public void enviarNoChannel01(View view) {
        String titulo = edtTitulo.getText().toString();
        String mensagem = edtMensagem.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NoticationReceiver.class);
        broadcastIntent.putExtra("toastMensagem", mensagem);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_01)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(titulo)
                .setContentText(mensagem)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    public void enviarNoChannel02(View view) {
        String titulo = edtTitulo.getText().toString();
        String mensagem = edtMensagem.getText().toString();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_02)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(titulo)
                .setContentText(mensagem)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManagerCompat.notify(2, notification);
    }
}
