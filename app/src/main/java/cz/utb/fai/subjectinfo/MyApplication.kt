package cz.utb.fai.subjectinfo

import android.app.Application
import cz.utb.fai.subjectinfo.api.StagApiService
import cz.utb.fai.subjectinfo.database.SubjectInfoDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    val apiService: StagApiService by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://stag-ws.utb.cz/ws/services/rest2/")
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON serialization/deserialization
            .build()

        retrofit.create(StagApiService::class.java)
    }

    val database: SubjectInfoDatabase by lazy {
        SubjectInfoDatabase.getDatabase(this)
    }

    val repository: Repository by lazy {
        Repository(apiService, database.subjectInfoDao())
    }

}