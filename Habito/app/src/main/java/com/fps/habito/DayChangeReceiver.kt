package com.fps.habito

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class DayChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

//        println("DAY CHANGE RECEIVED")
//        println(MainActivity.habits)

        for (h in Habit_Main_Screen.habits){
            h.progress = 0
            h.status = HabitStatus.NOT_STARTED
        }
        Habit_Main_Screen.habitAdapter.notifyDataSetChanged()

    }
}