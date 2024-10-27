package dev.charan.versiontracker.model

sealed class ProcessState{
    data class Success(val autoUpdateDTO: AutoUpdateDTO): ProcessState()
    object Loading: ProcessState()
    data class Error(val error:String): ProcessState()

}