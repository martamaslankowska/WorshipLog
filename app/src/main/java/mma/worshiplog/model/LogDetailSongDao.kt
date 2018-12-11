package mma.worshiplog.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query


@Dao
interface LogDetailSongDao {

    @Query("SELECT * FROM log_detail_song")
    fun getAllLogDetailSongs(): List<LogDetailSongEntity>

    @Query("SELECT * FROM log_detail_song WHERE id_song IN (:idSong)")
    fun getLogDetailSong(idSong: Int): List<LogDetailSongEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(logDetailSong: LogDetailSongEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(logDetailSong: List<LogDetailSongEntity>)

    @Query("DELETE FROM log_detail_song")
    fun deleteAll()

}