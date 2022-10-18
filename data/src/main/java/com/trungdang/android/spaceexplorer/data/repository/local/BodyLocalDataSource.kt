package com.trungdang.android.spaceexplorer.data.repository.local

import com.trungdang.android.spaceexplorer.data.model.BodyModel

interface BodyLocalDataSource {

    suspend fun getBody(): BodyModel


}