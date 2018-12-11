package mma.worshiplog.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "log_detail",
        primaryKeys = arrayOf("id_log", "id_song"),
        foreignKeys = arrayOf(
            ForeignKey(entity = LogFileEntity::class,
                parentColumns = arrayOf("id_log"), //parent id
                childColumns = arrayOf("id_log"), //this id
                onDelete = ForeignKey.CASCADE)))
data class LogDetailEntity(
        @ColumnInfo(name = "id_log") var idLog: Int,
        @ColumnInfo(name = "id_song") var idSong: Int,
        @ColumnInfo(name = "name_song") var nameSong: String?
) {
    constructor():this(0,0,"")

    companion object {
        fun initData(): List<LogDetailEntity> {
            return listOf(
                    LogDetailEntity(1, 1, "Błogosław Duszo moja Pana"),
                    LogDetailEntity(1, 2, "Tak jak Jeleń"),
                    LogDetailEntity(1, 3, "Who am I"),
                    LogDetailEntity(2, 4, "Tak jak Jeleń"),
                    LogDetailEntity(2, 5, "Credo"),
                    LogDetailEntity(2, 6, "Aż sponad mórz"),
                    LogDetailEntity(2, 7, "Pan jest pastrzem moim"),
                    LogDetailEntity(2, 8, "Blessed be Your name"),
                    LogDetailEntity(3, 9, "Choćbym szedł ciemną doliną"),
                    LogDetailEntity(4, 10, "Błogosławię Cię"))
        }
    }
}
