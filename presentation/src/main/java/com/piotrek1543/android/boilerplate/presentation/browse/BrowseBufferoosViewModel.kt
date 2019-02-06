package com.piotrek1543.android.boilerplate.presentation.browse

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.piotrek1543.android.boilerplate.domain.interactor.browse.GetBufferoos
import com.piotrek1543.android.boilerplate.domain.model.Bufferoo
import com.piotrek1543.android.boilerplate.presentation.data.Resource
import com.piotrek1543.android.boilerplate.presentation.data.ResourceState
import com.piotrek1543.android.boilerplate.presentation.mapper.BufferooMapper
import com.piotrek1543.android.boilerplate.presentation.model.BufferooView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

open class BrowseBufferoosViewModel @Inject internal constructor(
        private val getBufferoos: GetBufferoos,
        private val bufferooMapper: BufferooMapper) : ViewModel() {

    private val bufferoosLiveData: MutableLiveData<Resource<List<BufferooView>>> =
            MutableLiveData()

    init {
        fetchBufferoos()
    }

    override fun onCleared() {
        getBufferoos.dispose()
        super.onCleared()
    }

    fun getBufferoos(): LiveData<Resource<List<BufferooView>>> {
        return bufferoosLiveData
    }

    fun fetchBufferoos() {
        bufferoosLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBufferoos.execute(BufferooSubscriber())
    }

    inner class BufferooSubscriber : DisposableSubscriber<List<Bufferoo>>() {

        override fun onComplete() {}

        override fun onNext(t: List<Bufferoo>) {
            bufferoosLiveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { bufferooMapper.mapToView(it) }, null))
        }

        override fun onError(exception: Throwable) {
            bufferoosLiveData.postValue(Resource(ResourceState.ERROR, null, exception.message))
        }

    }

}