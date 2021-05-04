package asalcedo.com.beerapp.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val LEVEL_LOG = HttpLoggingInterceptor.Level.BODY

    val beerAPI: BeerService
        get() {
            val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(BeerService::class.java)
        }

    private val client: OkHttpClient
        get() {
            val builderClientHttp = OkHttpClient().newBuilder()
            if (ApiConstants.isDebugging) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = LEVEL_LOG
                builderClientHttp.addInterceptor(interceptor)
            }
            return builderClientHttp.build()
        }
}