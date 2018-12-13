package mma.worshiplog.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface LogDetailDao {

    @Query("SELECT * FROM log_detail")
    fun getAllLogDetails(): List<LogDetailEntity>

    @Query("SELECT * FROM log_detail WHERE id_log IN (:id)")
    fun getLogDetails(id: Int): List<LogDetailEntity>

    @Query("SELECT id_song FROM log_detail WHERE id_log IN (:id)")
    fun getLogDetailsSongsIds(id: Int): List<Int>

    @Query("SELECT id_log, log_detail.id_song, name_song, order_part, name_part, extra_info FROM log_detail LEFT JOIN log_detail_song ON log_detail.id_song = log_detail_song.id_song WHERE id_log IN (:id)")
    fun getAllLogDetails(id: Int): List<LogDetail>

    @Insert(onConflict = REPLACE)
    fun insert(logDetail: LogDetailEntity)

    @Insert(onConflict = REPLACE)
    fun insert(logDetail: List<LogDetailEntity>)

    @Query("DELETE FROM log_detail")
    fun deleteAll()

}