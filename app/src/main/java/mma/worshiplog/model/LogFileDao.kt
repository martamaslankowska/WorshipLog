package mma.worshiplog.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface LogFileDao {

    @Query("SELECT * FROM log_file")
    fun getAllLogs(): List<LogFileEntity>

    @Query("SELECT * FROM log_file WHERE id_log IN (:id)")
    fun getLog(id: Int): LogFileEntity

    @Insert(onConflict = REPLACE)
    fun insert(logFile: LogFileEntity)

    @Insert(onConflict = REPLACE)
    fun insert(logFile: List<LogFileEntity>)

    @Query("DELETE FROM log_file")
    fun deleteAll()

}