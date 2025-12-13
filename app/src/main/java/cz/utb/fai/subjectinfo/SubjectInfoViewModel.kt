package cz.utb.fai.subjectinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.utb.fai.subjectinfo.domain.SubjectInfoDomain
import kotlinx.coroutines.launch


class SubjectInfoViewModel(private val repository: Repository) : ViewModel() {

    private val _subjectInfoDomainValue = MutableLiveData<SubjectInfoDomain?>()
    val subjectInfoDomainValue: LiveData<SubjectInfoDomain?> = _subjectInfoDomainValue

    val showHint = MutableLiveData<Boolean>()
    val showNotFound = MutableLiveData<Boolean>()

    val zkratkaMutable = MutableLiveData<String?>()



    fun getSubjectInfo(katedra: String, zkratka: String){

        viewModelScope.launch {
            val result = repository.getSubjectInfo(katedra, zkratka)
            if(result != null) {
                _subjectInfoDomainValue.value = result
                showNotFound.value = false
            } else {
                showNotFound.value = true
            }
        }


    }

    fun search () {
        if (zkratkaMutable.value != null && !zkratkaMutable.value!!.isEmpty()) {
            // zkratka was provided by the user
            getSubjectInfo("AUIUI", zkratkaMutable.value!!)
        } else {
            // zkratka was not provided, show hint text view
            showHint.value = true
        }
    }

    fun hideHintAndNotFound () {
        showHint.value = false
        showNotFound.value = false
    }
}