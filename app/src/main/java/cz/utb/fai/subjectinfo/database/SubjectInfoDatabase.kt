package cz.utb.fai.subjectinfo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SubjectInfoEntity::class], version = 1, exportSchema = false)
abstract class SubjectInfoDatabase : RoomDatabase() {

    abstract fun subjectInfoDao(): SubjectInfoDao

    companion object {
        @Volatile
        private var INSTANCE: SubjectInfoDatabase? = null

        fun getDatabase(context: Context): SubjectInfoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubjectInfoDatabase::class.java,
                    "subject_info_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Useful for development, but remove for production.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
