package asalcedo.com.beerapp.services

import asalcedo.com.beerapp.data.Beer
import asalcedo.com.beerapp.services.ApiConstants.PARAMETER
import retrofit2.Response
import retrofit2.http.GET

interface BeerService {
    @GET(PARAMETER)
    suspend fun getBeers(): Response<List<Beer>>
}