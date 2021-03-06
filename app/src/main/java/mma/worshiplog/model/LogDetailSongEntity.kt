package mma.worshiplog.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "log_detail_song",
        primaryKeys = arrayOf("id_song", "order_part"),
        foreignKeys = arrayOf(
                ForeignKey(entity = PartNameEntity::class,
                        parentColumns = arrayOf("name_part"), //parent id
                        childColumns = arrayOf("name_part"), //this id
                        onDelete = ForeignKey.CASCADE)))
data class LogDetailSongEntity(
        @NonNull
        @ColumnInfo(name = "id_song") var idSong: Int,
        @NonNull
        @ColumnInfo(name = "order_part") var orderPart: Int,
        @ColumnInfo(name = "name_part") var namePart: String,
        @ColumnInfo(name = "extra_info") var extraInfo: String?
) {
    constructor():this(0,0, "", "")

    companion object {
        fun initData(): List<LogDetailSongEntity> {
            return listOf(
                    LogDetailSongEntity(1, 1, "zwr", ""),
                    LogDetailSongEntity(1, 2, "ref", "2"),
                    LogDetailSongEntity(1, 4, "zwr", ""),
                    LogDetailSongEntity(1, 5, "bridge", "4"),
                    LogDetailSongEntity(3, 1, "instr", "2"),
                    LogDetailSongEntity(3, 2, "zwr", ""),
                    LogDetailSongEntity(4, 1, "zwr", ""),
                    LogDetailSongEntity(4, 2, "ref", "4"),
                    LogDetailSongEntity(4, 3, "zwr", ""),
                    LogDetailSongEntity(5, 1, "ref", ""),
                    LogDetailSongEntity(8, 1, "zwr", ""),
                    LogDetailSongEntity(8, 2, "ref", "2"),
                    LogDetailSongEntity(8, 5, "zwr", ""),
                    LogDetailSongEntity(9, 1, "ref", ""))

        }
    }
}