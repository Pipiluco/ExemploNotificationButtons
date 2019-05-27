package br.com.lucasfsilva.exemplonotificationbuttons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NoticationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra("toastMensagem");
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }
}
