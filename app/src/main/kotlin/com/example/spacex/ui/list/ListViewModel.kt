package com.example.spacex.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetDataUseCase
import com.example.domain.model.ItemDomain
import com.example.spacex.utils.ItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getDataUseCase: GetDataUseCase) : ViewModel() {

    val pictureLiveData: MutableLiveData<ItemState> = MutableLiveData()

    val liveData: LiveData<ItemState>
        get() = pictureLiveData

    fun getPictures() {
        pictureLiveData.value = ItemState.ShowLoading
        getDataUseCase.execute(PictureObserver(), null)
    }

    private inner class PictureObserver : DisposableSubscriber<List<ItemDomain>>() {

        override fun onError(e: Throwable) {
            pictureLiveData.value = ItemState.HideLoading
            pictureLiveData.postValue(ItemState.Error(e!!))
        }


        override fun onNext(value: List<ItemDomain>?) {
            pictureLiveData.value = ItemState.HideLoading
            if (value!!.isEmpty())
                pictureLiveData.postValue(ItemState.Empty)
            else
                pictureLiveData.postValue(ItemState.Success(value))
        }

        override fun onComplete() {

        }


    }

}