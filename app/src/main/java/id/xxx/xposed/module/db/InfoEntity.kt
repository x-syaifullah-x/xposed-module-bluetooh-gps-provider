package id.xxx.xposed.module.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = InfoEntity.TABLE_NAME
)
data class InfoEntity(
    @PrimaryKey
    val id: Short = ID_FIX,

    @ColumnInfo(name = COLUMN_NAME_IS_RUNNING)
    val isRunning: Boolean,

    @ColumnInfo(name = COLUMN_NAME_DATA_NMEA)
    val dataNMEA: String
) {
    companion object {
        const val ID_FIX: Short = 1
        const val TABLE_NAME = "table_info"
        const val COLUMN_NAME_IS_RUNNING = "is_running"
        const val COLUMN_NAME_DATA_NMEA = "data_nmea"

        const val COLUMN_INDEX_IS_RUNNING = 1
        const val COLUMN_INDEX_DATA_NMEA = 2
    }
}