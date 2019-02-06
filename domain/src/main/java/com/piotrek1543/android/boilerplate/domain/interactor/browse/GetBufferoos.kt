package com.piotrek1543.android.boilerplate.domain.interactor.browse

import com.piotrek1543.android.boilerplate.domain.executor.PostExecutionThread
import com.piotrek1543.android.boilerplate.domain.executor.ThreadExecutor
import com.piotrek1543.android.boilerplate.domain.interactor.FlowableUseCase
import com.piotrek1543.android.boilerplate.domain.model.Bufferoo
import com.piotrek1543.android.boilerplate.domain.repository.BufferooRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [Bufferoo] instances from the [BufferooRepository]
 */
open class GetBufferoos @Inject constructor(val bufferooRepository: BufferooRepository,
                                            threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread) :
        FlowableUseCase<List<Bufferoo>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<Bufferoo>> {
        return bufferooRepository.getBufferoos()
    }

}