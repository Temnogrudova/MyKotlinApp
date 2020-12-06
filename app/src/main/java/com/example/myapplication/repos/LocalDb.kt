package com.example.myapplication.repos

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.goldtouch.ynet.repos.saved_articles.Item
import com.goldtouch.ynet.repos.saved_articles.SavedArticleDao

@Database(
    entities = [
        Item::class
    ],
    version = 1,
    exportSchema = false
)

abstract class LocalDb : RoomDatabase() {
    abstract fun savedArticle(): SavedArticleDao
}


val MIGRATION_FORM_1_TO_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `item`(`id` INTEGER NOT NULL,  `type` TEXT NOT NULL, `previewURL` TEXT NOT NULL, `webformatURL` TEXT NOT NULL, PRIMARY KEY(`id`, `type`))")
    }
}
