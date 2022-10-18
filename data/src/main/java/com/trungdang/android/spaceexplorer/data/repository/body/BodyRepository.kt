package com.trungdang.android.spaceexplorer.data.repository.body

import com.trungdang.android.spaceexplorer.data.model.BodyModel

interface BodyRepository {

    suspend fun getBody(id: String): BodyModel

}