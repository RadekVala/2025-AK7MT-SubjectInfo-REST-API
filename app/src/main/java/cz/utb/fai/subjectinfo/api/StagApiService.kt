package cz.utb.fai.subjectinfo.api

import cz.utb.fai.subjectinfo.model.SubjectInfoDomain
import retrofit2.http.GET
import retrofit2.http.Query

interface StagApiService {

    companion object {
        const val PATH = "predmety/getPredmetInfo"
    }
    /**
     * Fetches a list of subjects for a given department.
     * This is a suspend function to be used with coroutines.
     * The URL called will be: BASE_URL + "subject/getSubjectsForDepartment"
     * Example with query: BASE_URL + "subject/getSubjectsForDepartment?department=UAI"
     */
    @GET(PATH) // 4. Define the endpoint path
    suspend fun getSubjectInfo(
        @Query("katedra") katedra: String, // 5. Define a query parameter
        @Query("zkratka") zkratka: String, // 5. Define a query parameter
        @Query("outputFormat") outputFormat: String = "json" // 5. Define a query parameter
    ): SubjectInfoDomain? // 6. Define the return type
}