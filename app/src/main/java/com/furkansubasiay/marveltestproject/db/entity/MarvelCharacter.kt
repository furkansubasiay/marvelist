package com.furkansubasiay.marveltestproject.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */

@Entity(tableName = "characters")
data class MarvelCharacter(
    @ColumnInfo( name ="id") var character_id:Long,
    @ColumnInfo( name ="name") var name:String,
    @ColumnInfo(name ="description") var description:String,
    @ColumnInfo( name ="character_img_url") var character_img_url:String,
    @ColumnInfo(name="is_favorite") var is_favorite:Boolean=false
)
{
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}