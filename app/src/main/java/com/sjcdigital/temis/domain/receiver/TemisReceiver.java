package com.sjcdigital.temis.domain.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by bruno.santiago on 23/11/2016.
 */

public class TemisReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        configurarAlarme(context);
    }

    public static void configurarAlarme(Context contexto) {
        AlarmManager gerenciador = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        if (cal.getTimeInMillis() < System.currentTimeMillis()) {
            cal.add(Calendar.MONTH, 1);
        }

        gerenciador.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis()*30,
                AlarmManager.INTERVAL_DAY*30, obterIntentPendente(contexto));
    }

    private static PendingIntent obterIntentPendente(Context contexto) {
        Intent i = new Intent(contexto, TemisReceptor.class);
        return PendingIntent.getBroadcast(contexto, 0, i, 0);
    }

}
