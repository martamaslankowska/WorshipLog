package mma.worshiplog.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "part_name")
data class PartNameEntity(
        @PrimaryKey
        @ColumnInfo(name = "name_part") var namePart: String
) {
    constructor():this("")

    companion object {
        fun initData(): List<PartNameEntity> {
            return listOf(
                    PartNameEntity("intro"),
                    PartNameEntity("zwr"),
                    PartNameEntity("ref"),
                    PartNameEntity("bridge"))
        }
    }
}