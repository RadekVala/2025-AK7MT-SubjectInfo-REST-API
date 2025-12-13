package cz.utb.fai.subjectinfo

import android.util.Log
import cz.utb.fai.subjectinfo.api.StagApiService
import cz.utb.fai.subjectinfo.database.SubjectInfoDatabase
import cz.utb.fai.subjectinfo.mappers.asDomainModel
import cz.utb.fai.subjectinfo.model.SubjectInfoDomain

class Repository (
    private val apiService: StagApiService,
    private val database: SubjectInfoDatabase
) {
    suspend fun getSubjectInfo(katedra: String, zkratka: String): SubjectInfoDomain? {
        return try {
            apiService.getSubjectInfo(katedra, zkratka)?.asDomainModel()
        } catch (e: Exception) {
            // Log the error and return null to signal failure
            Log.e("Repository", "API call failed", e)
            null
        }
    }
}