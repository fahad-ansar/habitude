package com.fps.habito

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.GridView

class IconPickerActivity : AppCompatActivity() {

    private val iconsGrid: GridView by lazy { findViewById(R.id.iconsGrid) }

    private val allIcons = arrayListOf(
        R.drawable.dumbbell,
        R.drawable.strong,
        R.drawable.jogging,
        R.drawable.stretching,
        R.drawable.walk,
        R.drawable.bath,
        R.drawable.grooming,
        R.drawable.wardrobe,
        R.drawable.glass_of_water,
        R.drawable.binary_code,
        R.drawable.study,
        R.drawable.housekeeping,
        R.drawable.drugs,
        R.drawable.pet,
        R.drawable.cooking,
        R.drawable.double_bed,
        R.drawable.healthy_food,
        R.drawable.eating,
        R.drawable.idea,
        R.drawable.money_bag,
        R.drawable.park,
        R.drawable.sapling,
        R.drawable.clock,
        R.drawable.sleep,
        R.drawable.wake_up,
        R.drawable.toothbrush,
        R.drawable.no_junk_food,
        R.drawable.no_alcohol,
        R.drawable.no_phone,
        R.drawable.no_smoking,
        R.drawable.no_sugar,
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_icon_picker)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primary_pink)))
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.primary_pink)
        title = "Pick an icon"

        iconsGrid.adapter = IconAdapter(this, allIcons)
        sendSelectedIcon()
    }

        private fun sendSelectedIcon() {

        iconsGrid.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val habitFormIntent = Intent(this, FormActivity::class.java)
            habitFormIntent.putExtra("selected_icon", allIcons[position])
            setResult(500, habitFormIntent)
            finish()
        }

    }
}

