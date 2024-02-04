package com.register.festapp.domain.useCases

import com.register.festapp.domain.entities.UserData
import com.register.festapp.domain.repository.ShopRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun getUserData(): UserData = repository.getUserData()

}