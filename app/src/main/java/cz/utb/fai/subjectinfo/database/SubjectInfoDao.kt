package cz.utb.fai.subjectinfo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SubjectInfoDao {

    // Using REPLACE strategy so if we fetch updated info for the same subject, it updates the DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subjectInfo: SubjectInfoEntity)

    // Select specific subject by shortcut and department (or just shortcut if unique)
    @Query("SELECT * FROM subject_info WHERE shortcut = :shortcut LIMIT 1")
    suspend fun selectByShortcut(shortcut: String): SubjectInfoEntity?

    // Optional: Keep a history or list of all cached subjects
    @Query("SELECT * FROM subject_info")
    suspend fun getAllSubjects(): List<SubjectInfoEntity>
}
