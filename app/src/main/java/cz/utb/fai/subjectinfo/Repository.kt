package cz.utb.fai.subjectinfo

import android.util.Log
import cz.utb.fai.subjectinfo.api.StagApiService
import cz.utb.fai.subjectinfo.model.SubjectInfo

class Repository (private val apiService: StagApiService) {
    suspend fun getSubjectInfo(katedra: String, zkratka: String): SubjectInfo? {
        return try {
            apiService.getSubjectInfo(katedra, zkratka)
        } catch (e: Exception) {
            // Log the error and return null to signal failure
            Log.e("Repository", "API call failed", e)
            null
        }
    }
}