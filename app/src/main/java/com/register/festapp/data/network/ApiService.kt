package com.register.festapp.data.network

import com.register.festapp.data.network.model.ShopInfoDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getShopInfo(): Response<ShopInfoDto>

}