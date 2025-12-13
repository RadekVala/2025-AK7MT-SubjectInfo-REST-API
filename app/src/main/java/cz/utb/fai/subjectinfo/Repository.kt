package cz.utb.fai.subjectinfo

import android.util.Log
import cz.utb.fai.subjectinfo.api.StagApiService
import cz.utb.fai.subjectinfo.database.SubjectInfoDao
import cz.utb.fai.subjectinfo.domain.SubjectInfoDomain
import cz.utb.fai.subjectinfo.mappers.asDomainModel
import cz.utb.fai.subjectinfo.mappers.asEntityModel

class Repository (
    private val apiService: StagApiService,
    private val dao: SubjectInfoDao
) {
    suspend fun getSubjectInfo(katedra: String, zkratka: String): SubjectInfoDomain? {
        try {
            // 1. Try to fetch from Network
            val networkModel = apiService.getSubjectInfo(katedra, zkratka)

            if (networkModel != null) {
                val domainModel = networkModel.asDomainModel()

                // 2. Save/Cache into Database
                // Ensure you have creating the mapping function: SubjectInfoDomain.asEntityModel()
                dao.insert(domainModel.asEntityModel())

                Log.d("Repository", "Data loaded from API and cached.")
                return domainModel
            }
        } catch (e: Exception) {
            // Log the error and return null to signal failure
            Log.e("Repository", "API call failed", e)

        }
        // 3. Fallback: Try to fetch from Database (Cache)
        try {
            val entity = dao.selectByShortcut(zkratka)
            if (entity != null) {
                Log.d("Repository", "Data loaded from Database cache.")
                return entity.asDomainModel()
            }
        } catch (e: Exception) {
            Log.e("Repository", "Database fallback failed", e)
        }

        return null
    }
}