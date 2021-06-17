package com.fps.habito

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.CalendarView
import android.widget.Toast
import java.util.*

class HabitResetReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        intent?.getParcelableArrayListExtra<Habit>("all_habits")!!.forEach {

            if (it. progress.status != Status.COMPLETED.toString()) {
                it.stats.streak = 0
            }

            it. progress.progress = 0
            it. progress.status = Status.NOT_STARTED.toString()

        }

        context?.sendBroadcast(Intent("NotifyAndBackup"))

    }

}

