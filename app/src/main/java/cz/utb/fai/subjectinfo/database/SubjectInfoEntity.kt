package cz.utb.fai.subjectinfo.database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_info")
data class SubjectInfoEntity(
    @PrimaryKey()
    val shortcut: String,
    val name: String,
    val credits: Int,
    val department: String,
    val description: String
)