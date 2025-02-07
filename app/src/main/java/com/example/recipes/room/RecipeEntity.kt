package com.example.recipes.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name : String,
    val description : String,
//    val imageData : ByteArray
    val imageUri: String
)
