package mma.worshiplog.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask.execute
import android.arch.persistence.db.SupportSQLiteDatabase
import io.reactivex.annotations.NonNull
import java.util.concurrent.Executors


@Database(entities = arrayOf(LogFileEntity::class, LogDetailEntity::class, LogDetailSongEntity::class, PartNameEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun logFileDao(): LogFileDao
    abstract fun logDetailDao(): LogDetailDao
    abstract fun logDetailSongDao(): LogDetailSongDao
    abstract fun partNameDao(): PartNameDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase::class.java, "worship_log.db")
                            //.allowMainThreadQueries()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Executors.newSingleThreadScheduledExecutor()
                                            .execute(Runnable {
                                                getInstance(context)?.partNameDao()?.insert(PartNameEntity.initData())
                                                getInstance(context)?.logFileDao()?.insert(LogFileEntity.initData())
                                                getInstance(context)?.logDetailDao()?.insert(LogDetailEntity.initData())
                                                getInstance(context)?.logDetailSongDao()?.insert(LogDetailSongEntity.initData())
                                            })
                                }
                            }).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}


