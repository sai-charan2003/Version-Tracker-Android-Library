package dev.charan.versiontracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.charan.versiontracker.model.ProcessState
import dev.charan.versiontracker.retrofit.RetrofitAPIClient

object VersionTracker {

    suspend fun getLatestAppVersion(appName : String, apiKey : String) : LiveData<ProcessState> {
        val processState= MutableLiveData<ProcessState>(ProcessState.Loading)
        return try {

            val response = RetrofitAPIClient.apiInterface.fetchLatestAppVersion(appName = appName,apiKey = apiKey)
            if (response.isSuccessful) {
                val data = response.body()
                processState.postValue(data?.let { ProcessState.Success(it) })
                processState
            } else {
                Log.e("APICall", "Failed to fetch app version. Error code: ${response.code()}")
                processState.postValue(ProcessState.Error("Unable to fetch the data"))
                processState
            }
        } catch (e: Exception) {
            Log.e("APICall", "getLatestAppVersionFromAPI: $e")
            processState.postValue(ProcessState.Error("Unable to fetch the data"))
            processState

        }
    }
}