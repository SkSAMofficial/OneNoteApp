package com.example.onenot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteTable")
class Note(@ColumnInfo(name ="title")val noteTitle:String,@ColumnInfo(name = "description")val noteDescription:String,
            @ColumnInfo(name = "timeStamp") val timeStamp:String
           ) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}