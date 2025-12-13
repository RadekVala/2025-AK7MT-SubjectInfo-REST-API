package cz.utb.fai.subjectinfo.model

data class SubjectInfoDomain(
    val name: String,
    val shortcut: String,
    val credits: Int,
    val description: String,
    val department: String
)