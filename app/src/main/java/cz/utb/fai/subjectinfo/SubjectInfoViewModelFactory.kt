package cz.utb.fai.subjectinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.subjectinfo.Repository

class SubjectInfoViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested ViewModel class is our SubjectInfoViewModel
        if (modelClass.isAssignableFrom(SubjectInfoViewModel::class.java)) {
            // If it is, create and return an instance, passing the repository.
            // The 'as T' cast is safe because of the isAssignableFrom check.
            @Suppress("UNCHECKED_CAST")
            return SubjectInfoViewModel(repository) as T
        }
        // If it's a different ViewModel, throw an exception.
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
