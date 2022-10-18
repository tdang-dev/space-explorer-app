package com.trungdang.android.spaceexplorer.data.repository.body

import com.trungdang.android.spaceexplorer.data.model.BodyModel
import com.trungdang.android.spaceexplorer.data.repository.local.BodyLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class BodyRepositoryImpl(
    private val bodyLocalDataSource: BodyLocalDataSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
): BodyRepository {

    override suspend fun getBody(id: String): BodyModel {
        return bodyLocalDataSource.getBody()
    }

}