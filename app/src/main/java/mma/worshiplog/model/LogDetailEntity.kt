package mma.worshiplog.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "log_detail",
        foreignKeys = arrayOf(
            ForeignKey(entity = LogFileEntity::class,
                parentColumns = arrayOf("id_log"), //parent id
                childColumns = arrayOf("id_log"), //this id
                onDelete = ForeignKey.CASCADE)))
data class LogDetailEntity(
        @PrimaryKey
        @ColumnInfo(name = "id_log") var idLog: Int,
        @ColumnInfo(name = "id_song") var idSong: Int,
        @ColumnInfo(name = "name_song") var nameSong: String?
) {
    constructor():this(0,0,"")
}
