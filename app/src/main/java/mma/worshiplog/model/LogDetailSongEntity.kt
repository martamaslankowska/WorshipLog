package mma.worshiplog.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "log_detail_song",
        primaryKeys = arrayOf("id_song", "name_part"),
        foreignKeys = arrayOf(
                ForeignKey(entity = PartNameEntity::class,
                        parentColumns = arrayOf("name_part"), //parent id
                        childColumns = arrayOf("name_part"), //this id
                        onDelete = ForeignKey.CASCADE)))
data class LogDetailSongEntity(
        @NonNull
        @ColumnInfo(name = "id_song") var idSong: Int,
        @ColumnInfo(name = "order_part") var orderPart: Int?,
        @NonNull
        @ColumnInfo(name = "name_part") var namePart: String,
        @ColumnInfo(name = "extra_info") var extraInfo: String?
) {
    constructor():this(0,0, "", "")
}