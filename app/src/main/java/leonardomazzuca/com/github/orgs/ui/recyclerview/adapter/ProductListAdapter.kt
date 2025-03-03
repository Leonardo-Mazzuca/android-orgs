package leonardomazzuca.com.github.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import leonardomazzuca.com.github.orgs.R
import leonardomazzuca.com.github.orgs.model.Product

class ProductListAdapter(
    private val context: Context,
    products: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {


    private val products = products.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun vinculate(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.product_item_name)
            val description = itemView.findViewById<TextView>(R.id.product_item_description)
            val price = itemView.findViewById<TextView>(R.id.product_item_price)

            name.text = product.name
            description.text = product.description
            price.text = product.price.toPlainString()

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.product_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        holder.vinculate(product)

    }

    fun update(products: List<Product>) {

        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()

    }

    fun filterList(search: String, productsToFilter:List<Product>) : List<Product> {

        return productsToFilter.filter { p -> p.name.lowercase().trim().contains(search.lowercase().trim()) }

    }

}
