package id.xxx.xposed.module.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query
import id.xxx.xposed.module.db.InfoEntity.Companion.COLUMN_NAME_DATA_NMEA
import id.xxx.xposed.module.db.InfoEntity.Companion.COLUMN_NAME_IS_RUNNING
import id.xxx.xposed.module.db.InfoEntity.Companion.ID_FIX
import id.xxx.xposed.module.db.InfoEntity.Companion.TABLE_NAME

@Dao
interface InfoDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun get(): Cursor?

    @Query("UPDATE $TABLE_NAME SET $COLUMN_NAME_IS_RUNNING = :isRunning WHERE id =$ID_FIX")
    fun setIsRunning(isRunning: Boolean): Int

    @Query("UPDATE $TABLE_NAME SET $COLUMN_NAME_DATA_NMEA = :dataNmea WHERE id =$ID_FIX")
    fun setDataNmea(dataNmea: String): Int
}