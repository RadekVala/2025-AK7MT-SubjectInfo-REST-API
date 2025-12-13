package cz.utb.fai.subjectinfo.mappers

// In a new file 'Mappers.kt' or at the bottom of Repository.kt
import cz.utb.fai.subjectinfo.model.SubjectInfoDomain
import cz.utb.fai.subjectinfo.api.SubjectInfoNetwork // Assuming this is your API model

fun SubjectInfoNetwork.asDomainModel(): SubjectInfoDomain {
    return SubjectInfoDomain(
        name = this.nazev,
        shortcut = this.zkratka,
        credits = this.kreditu.toInt(),
        department = this.katedra,
        description = this.anotace
    )
}
