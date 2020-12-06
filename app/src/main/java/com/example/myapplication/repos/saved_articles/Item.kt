package com.goldtouch.ynet.repos.saved_articles

import androidx.room.*
import com.example.myapplication.models.PixabayModel

@Entity(tableName = "item", primaryKeys = ["id", "type"])
data class Item(
    var id: Long,
    var type: String = TYPE_NORMAL,
    var previewURL: String = "",
    var webformatURL: String = ""
    ) {

    constructor(item: PixabayModel.Image,  type: String) :
            this(
                id = item.id!!,
                type = type,
                previewURL = item.previewURL?:"",
                webformatURL = item.webformatURL?:""
            )
    companion object {
        const val TYPE_FAVOURITE = "TYPE_FAVOURITE"
        const val TYPE_NORMAL = "TYPE_NORMAL"
    }
}
