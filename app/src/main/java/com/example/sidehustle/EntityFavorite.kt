package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "favorite_table",
    foreignKeys = [
        ForeignKey(
            entity = EntityEmployee::class,
            parentColumns = ["employeeID"],
            childColumns = ["employeeID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = EntityJob::class,
            parentColumns = ["jobID"],
            childColumns = ["jobID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ], primaryKeys = ["employeeID", "jobID"]
)
data class EntityFavorite(
    val employeeID: Long,
    val jobID: Long
)

// Sample to populate the entity

//FavoriteEntity(1,1)
//FavoriteEntity(2,2)
//FavoriteEntity(3,3)
