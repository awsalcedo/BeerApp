package asalcedo.com.beerapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import asalcedo.com.beerapp.adapter.BeerAdapter
import asalcedo.com.beerapp.data.Beer
import asalcedo.com.beerapp.databinding.ActivityMainBinding
import asalcedo.com.beerapp.services.BeerService
import asalcedo.com.beerapp.services.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BeerAdapter
    private val beerList = mutableListOf<Beer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initRecyclerView()

        getBeers()



    }

    private fun getBeers(){
        val internetConnection = (application as MyApplication).hasInternetConnection()
        if (internetConnection) {
            callBeersAPI()
        }
    }

    private fun callBeersAPI(){
        CoroutineScope(Dispatchers.IO).launch {

            var beerService: BeerService = RetrofitFactory.beerAPI
            val call = beerService.getBeers()

            val beers = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    beerList.clear()
                    if (beers != null) {
                        beerList.addAll(beers)
                    }
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }
            }
        }
    }

    private fun initRecyclerView(){
        adapter = BeerAdapter(beerList)
        binding.rvBeer.setHasFixedSize(true)
        binding.rvBeer.layoutManager = LinearLayoutManager(this)
        binding.rvBeer.adapter = adapter
    }

    private fun showError(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}