package cz.utb.fai.subjectinfo.mappers

import cz.utb.fai.subjectinfo.api.SubjectInfoNetwork
import cz.utb.fai.subjectinfo.database.SubjectInfoEntity
import cz.utb.fai.subjectinfo.domain.SubjectInfoDomain

fun SubjectInfoNetwork.asDomainModel(): SubjectInfoDomain {
    return SubjectInfoDomain(

        name = this.nazev,
        shortcut = this.zkratka,
        credits = this.kreditu,
        department = this.katedra,
        description = this.anotace
    )
}

fun SubjectInfoDomain.asEntityModel(): SubjectInfoEntity {
    return SubjectInfoEntity(
        name = this.name,
        shortcut = this.shortcut,
        credits = this.credits,
        description = this.description,
        department = this.department
    )
}

fun SubjectInfoEntity.asDomainModel(): SubjectInfoDomain {
    return SubjectInfoDomain(
        name = this.name,
        shortcut = this.shortcut,
        credits = this.credits,
        description = this.description,
        department = this.department
    )
}