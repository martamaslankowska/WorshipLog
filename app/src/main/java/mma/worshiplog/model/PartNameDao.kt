package mma.worshiplog.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query


@Dao
interface PartNameDao {

    @Query("SELECT * FROM part_name")
    fun getAllPartNames(): List<PartNameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(partName: List<PartNameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(partName: PartNameEntity)

    @Query("DELETE FROM part_name")
    fun deleteAll()

}