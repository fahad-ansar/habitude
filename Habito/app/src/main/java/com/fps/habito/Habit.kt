package com.fps.habito

import android.os.Parcel
import android.os.Parcelable

class Habit(
        var icon: Int = R.drawable.nil,
        var name: String,
        var desc: String = "",
        var steps: Int = 1,
        var streak: Int = 0,
        var allTime: Double = 0.0,
        var comp: Int = 0,
        var habitReminder: HabitReminder = HabitReminder()
) : Parcelable {

    var progress = 0
    var status = HabitStatus.NOT_STARTED

    fun updateProgress() {

        if (progress < steps) {
            ++progress
            status = HabitStatus.IN_PROGRESS
        }

        if (progress == steps && status != HabitStatus.COMPLETED) {
            status = HabitStatus.COMPLETED
            ++streak
            ++comp
        }

    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Habit> {
            override fun createFromParcel(parcel: Parcel) = Habit(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Habit>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readParcelable<HabitReminder>(HabitReminder::class.java.classLoader)!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(icon)
        dest?.writeString(name)
        dest?.writeString(desc)
        dest?.writeInt(steps)
        dest?.writeInt(streak)
        dest?.writeDouble(allTime)
        dest?.writeInt(comp)
        dest?.writeParcelable(habitReminder, flags)
    }

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Habit

        if (name != other.name) return false

        return true

    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + desc.hashCode()
        result = 31 * result + steps
        return result
    }


    override fun toString(): String {
        return "Habit(icon=$icon, name='$name', desc='$desc', steps=$steps, streak=$streak, allTime=$allTime, comp=$comp, progress=$progress, status='$status' HabitReminder($habitReminder))"
    }


}