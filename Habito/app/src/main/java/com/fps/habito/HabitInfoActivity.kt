package com.fps.habito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import java.sql.SQLOutput

class HabitInfoActivity : AppCompatActivity() {

    private val habitName: TextView by lazy { findViewById(R.id.habitName) }
    private val desc: TextView by lazy { findViewById(R.id.desc) }
    private val steps: TextView by lazy { findViewById(R.id.steps) }

    private val streak: TextView by lazy { findViewById(R.id.streakValue) }
    private val alltime: TextView by lazy { findViewById(R.id.alltimeValue) }
    private val comp: TextView by lazy { findViewById(R.id.compValue) }

    private val edit: Button by lazy { findViewById(R.id.edit) }
    private val delete: Button by lazy { findViewById(R.id.delete) }

    private val oldHabitName by lazy { intent.getStringArrayListExtra("habit_info")!![0] }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_info)

        if (intent.getStringExtra("PARENT_ACTIVITY_NAME").equals("MAIN")) {
            fillFormFields()
            deleteHabit()
        }

        editButtonListener()

    }

    override fun onBackPressed() {

        val mainIntent = Intent(this, MainActivity::class.java)

        println("INFO $oldHabitName ${habitName.text}")

        if (!oldHabitName.equals(habitName.text)) {
            mainIntent.putStringArrayListExtra("habit_for_main",
                    arrayListOf(
                            oldHabitName,
                            habitName.text.toString(),
                            desc.text.toString(),
                            steps.text.toString(),
                    )
            )
        }



        setResult(400, mainIntent)
        finish()

    }

    private fun fillFormFields() {
        val habitInfo = intent.getStringArrayListExtra("habit_info")!!

        habitName.text = habitInfo[0]
        desc.text = habitInfo[1]

        steps.text = habitInfo[2]

        streak.text = habitInfo[3]
        alltime.text = habitInfo[4]
        comp.text = habitInfo[5]
    }

    /**
     * Opens HabitForm and sends name, desc and steps.
     */
    private fun editButtonListener() {

        edit.setOnClickListener {
            val habitFormIntent = Intent(applicationContext, HabitFormActivity::class.java)

            val habitInfo = intent.getStringArrayListExtra("habit_info")!!

            habitFormIntent.putExtra("PARENT_ACTIVITY_NAME", "HABIT_INFO")
            habitFormIntent.putStringArrayListExtra("habit_filled_info",
                    arrayListOf(
                            habitInfo[0],
                            habitInfo[1],
                            habitInfo[2],
                    )
            )

            startActivityForResult(habitFormIntent, 300)
        }

    }

    private fun deleteHabit() {

        delete.setOnClickListener {
            val mainActIntent = Intent(applicationContext, MainActivity::class.java)

            mainActIntent.putExtra("del_habit", habitName.text)

            setResult(200, mainActIntent)
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == 300) {
            val updatedHabit = data!!.getStringArrayListExtra("updated_habit")!!

            habitName.text = updatedHabit[0]
            desc.text = updatedHabit[1]
            steps.text = updatedHabit[2]

        }
    }


}