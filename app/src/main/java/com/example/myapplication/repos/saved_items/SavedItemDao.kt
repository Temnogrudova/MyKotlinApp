package  com.example.myapplication.repos.saved_items


import androidx.room.*

@Dao
abstract class SavedItemDao {

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
    abstract fun getFavouriteItems(): List<Item>


}
