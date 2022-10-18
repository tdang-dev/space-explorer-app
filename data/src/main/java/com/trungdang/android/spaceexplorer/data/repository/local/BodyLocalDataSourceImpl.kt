package com.trungdang.android.spaceexplorer.data.repository.local

import com.trungdang.android.spaceexplorer.data.model.BodyModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class BodyLocalDataSourceImpl(
    private val defaultCoroutineDispatcher: CoroutineDispatcher
): BodyLocalDataSource {

    override suspend fun getBody(): BodyModel {
        return withContext(defaultCoroutineDispatcher) {
            BodyModel(
                id = "terre",
                name = "La Terre",
                englishName = "Earth",
                isPlanet = true
            )
        }
    }
}