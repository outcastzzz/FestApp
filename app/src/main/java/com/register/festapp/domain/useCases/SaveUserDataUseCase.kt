package com.register.festapp.domain.useCases

import com.register.festapp.domain.entities.UserData
import com.register.festapp.domain.repository.ShopRepository
import javax.inject.Inject

class SaveUserDataUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun saveUserData(data: UserData) = repository.saveUserData(data)

}