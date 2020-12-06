package com.goldtouch.ynet.repos.saved_articles

import androidx.room.*

@Dao
abstract class SavedArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: Item)

    @Query("DELETE FROM item WHERE id = :id")
    abstract fun deleteByID(id :String)

    @Delete
    abstract fun delete(item: Item)


    //==============  Favourite  ====================
    @Query("DELETE FROM item WHERE type LIKE 'TYPE_FAVOURITE'")
    abstract fun deleteAllFavourite()

    @Query("SELECT * FROM item WHERE type LIKE 'TYPE_FAVOURITE'")
    abstract fun getFavouriteArticles(): List<Item>


}
