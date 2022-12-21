package com.example.domain.usecases

import com.example.domain.repository.LocalRepository
import com.example.domain.model.ItemDomain
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val localRepository: LocalRepository) :
    FlowableUseCase<List<ItemDomain>, Void>() {
    override fun buildUseCaseObservable(params: Void?): Flowable<List<ItemDomain>> {
        return localRepository.getData()
    }
}