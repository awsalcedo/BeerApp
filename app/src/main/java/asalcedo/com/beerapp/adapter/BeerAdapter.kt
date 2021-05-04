package asalcedo.com.beerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import asalcedo.com.beerapp.R
import asalcedo.com.beerapp.data.Beer
import asalcedo.com.beerapp.databinding.ItemBeerBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class BeerAdapter(private var beerList: List<Beer>): RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    inner class BeerViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemBeerBinding.bind(view)

        fun bind(beer: Beer) {
            binding.tvTagline.text = beer.tagline
            binding.tvDescription.text = beer.description
            binding.tvName.text = beer.name
            binding.tvFirstBrewed.text = beer.first_brewed
            Glide.with(itemView)
                .load(beer.image_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_beer, parent, false)
        return BeerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val item = beerList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = beerList.size
}