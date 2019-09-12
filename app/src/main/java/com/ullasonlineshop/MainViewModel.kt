package com.ullasonlineshop
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ullasonlineshop.models.ResourceStatus
import com.ullasonlineshop.data.repository.KartRepository
import com.ullasonlineshop.models.KartContent
import javax.inject.Inject

class MainViewModel @Inject constructor(private val kartRepository: KartRepository) : ViewModel() {

    private val content = MutableLiveData<ResourceStatus<KartContent>>()

    init {
        print("MainViewModel initialized")
        pullContent()
    }

    fun pullContent() {
        content.value = ResourceStatus.Loading()
        kartRepository.getContent(
            {
                content.value = ResourceStatus.Success(it)
            }, {
                content.value = ResourceStatus.Error(it)
            }
        )
    }

    fun getContent(): LiveData<ResourceStatus<KartContent>> {
        return content
    }
}