package mma.worshiplog.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "log_file")
data class LogFileEntity(
        @PrimaryKey
        @ColumnInfo(name = "id_log") var idLog: Int,
        @ColumnInfo(name = "log_date") var logDate: String?,
        @ColumnInfo(name = "log_name") var logName: String?
) {
    constructor():this(0,"","")


    companion object {
        fun initData(): List<LogFileEntity> {
            return listOf(
                    LogFileEntity(1, "8-12-2018", "próba nabożeństwa"),
                    LogFileEntity(2, "3-12-2018", "próba"),
                    LogFileEntity(3, "18-11-2018", "próba do wieczoru uwielbienia"),
                    LogFileEntity(4, "23-10-2018", "próba w kościele"))
        }
    }
}
