package id.xxx.xposed.module.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.net.Uri
import id.xxx.xposed.module.db.Database
import id.xxx.xposed.module.db.InfoDao
import id.xxx.xposed.module.db.InfoEntity

class DatabaseProvider : ContentProvider() {

    companion object {
        private const val INFO = 1

        const val AUTHORITY = "id.xxx.xposed.module"
        private const val SCHEME = "content"

        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(InfoEntity.TABLE_NAME)
            .build()

        fun getIsRunning(context: Context?): Boolean {
            val cursor = context?.contentResolver?.query(
                CONTENT_URI, null, null, null, null
            ) ?: return false

            var result = false
            if (cursor.move(1)) {
                result = cursor.getInt(InfoEntity.COLUMN_INDEX_IS_RUNNING) > 0
            }
            cursor.close()
            return result
        }

        fun getDataNmea(context: Context?): String {
            val cursor = context?.contentResolver?.query(
                CONTENT_URI, null, null, null, null
            ) ?: return ""

            var result: String = ""
            if (cursor.move(1)) {
                result = cursor.getString(InfoEntity.COLUMN_INDEX_DATA_NMEA)
            }
            cursor.close()
            return result
        }

        fun setIsRunning(context: Context?, b: Boolean): Uri? {
            return context?.contentResolver?.insert(
                CONTENT_URI,
                ContentValues().apply { put(InfoEntity.COLUMN_NAME_IS_RUNNING, b) }
            )
        }

        fun setDataNmea(context: Context?, strNmea: String?): Uri? {
            return context?.contentResolver?.insert(
                CONTENT_URI,
                ContentValues().apply { put(InfoEntity.COLUMN_NAME_DATA_NMEA, strNmea) }
            )
        }
    }

    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private lateinit var info: InfoDao

    init {
        sUriMatcher.addURI(
            AUTHORITY, InfoEntity.TABLE_NAME, INFO
        )
    }


    override fun onCreate(): Boolean {
        info = Database.instance(context ?: return false).infoDao()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ) = when (sUriMatcher.match(uri)) {
        INFO -> info.get()
        else -> null
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        values ?: return null
        val res = when (sUriMatcher.match(uri)) {
            INFO -> {
                when {
                    values.containsKey(InfoEntity.COLUMN_NAME_IS_RUNNING) -> {
                        info.setIsRunning(
                            values.getAsBoolean(InfoEntity.COLUMN_NAME_IS_RUNNING) ?: return null
                        )
                    }

                    values.containsKey(InfoEntity.COLUMN_NAME_DATA_NMEA) -> {
                        info.setDataNmea(
                            values.getAsString(InfoEntity.COLUMN_NAME_DATA_NMEA) ?: return null
                        )
                    }
                    else -> null
                }
            }
            else -> null
        }
        if (res != null) context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return Uri.parse("$CONTENT_URI/$res")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

}