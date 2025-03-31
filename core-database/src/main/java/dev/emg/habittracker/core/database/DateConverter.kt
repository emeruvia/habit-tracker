package dev.emg.habittracker.core.database

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}

fun Date.toFormattedString(): String {
    val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return dateFormat.format(this)
}