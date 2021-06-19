package id.xxx.xposed.module.db

import android.content.ContentValues
import android.content.Context
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@androidx.room.Database(
    entities = [
        InfoEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class Database : RoomDatabase() {

    companion object {

        @Volatile
        private var instance: Database? = null

        fun instance(context: Context) = instance ?: synchronized(Database::class.java) {
            instance ?: Room
                .databaseBuilder(context, Database::class.java, "database")
                .allowMainThreadQueries()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        db.insert(
                            InfoEntity.TABLE_NAME,
                            OnConflictStrategy.REPLACE,
                            ContentValues().apply {
                                put(InfoEntity.COLUMN_NAME_IS_RUNNING, false)
                                put(InfoEntity.COLUMN_NAME_DATA_NMEA, "")
                            }
                        )
                    }
                }).build()
                .also { database -> instance = database }
        }
    }


    abstract fun infoDao(): InfoDao
}