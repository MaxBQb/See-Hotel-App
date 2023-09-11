package com.example.chotel.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.annotation.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface HotelRemoteDataSource {
    suspend fun getHotelInfo(): Result<HotelRemoteDTO>
    suspend fun getHotelRooms(): Result<HotelRoomsRemoteDTO>
    suspend fun getRoomBookingDetails(): Result<HotelRoomBookingDetailsRemoteDTO>
}

@Singleton
class HotelRemoteDataSourceImpl : HotelRemoteDataSource {
    private val api = buildRetrofitClient()

    override suspend fun getHotelInfo() = catchResponse {
        api.getHotelInfo()
    }

    override suspend fun getHotelRooms()=catchResponse {
        api.getHotelRooms()
    }

    override suspend fun getRoomBookingDetails() = catchResponse {
        api.getRoomBookingDetails()
    }

    private inline fun <T> catchResponse(block: () -> T) = runCatching { block() }

    companion object {
        const val URL = "https://run.mocky.io/v3/"
        private const val TIMEOUT = 30L

        private fun buildRetrofitClient() = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(converter)
            .client(okhttpClient)
            .build()
            .create(CHotelAPI::class.java)


        private val okhttpClient
            get() = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()

        private val converter
            get() = GsonBuilder().apply {
                setLenient()
            }.create().run {
                GsonConverterFactory.create(this)
            }
    }
}