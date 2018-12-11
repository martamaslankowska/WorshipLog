package mma.worshiplog.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "log_file")
data class LogFileEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_log") var idLog: Int,
        @ColumnInfo(name = "log_date") var logDate: String?,
        @ColumnInfo(name = "log_name") var logName: String?
) {
    constructor():this(0,"","")
}
