package leonardomazzuca.com.github.orgs.ui.activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import leonardomazzuca.com.github.orgs.R
import leonardomazzuca.com.github.orgs.dao.ProductsDAO
import leonardomazzuca.com.github.orgs.ui.recyclerview.adapter.ProductListAdapter


class ProductsListActivity : AppCompatActivity(R.layout.activity_product_list){


    private val dao = ProductsDAO()
    private val adapter = ProductListAdapter(context = this, products = dao.getProducts())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configRecyclerView()
        configFab()
        filterList()
    }
    
    override fun onResume() {
        super.onResume()
        adapter.update(dao.getProducts())
    }

    private fun configFab() {
        val floatBtn = findViewById<FloatingActionButton>(R.id.product_list_fab)
        //código de navegação
        floatBtn.setOnClickListener {
            goToProductForm()
        }
    }

    private fun goToProductForm() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun configRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.product_list)
        recyclerView.adapter = adapter
    }

    private fun filterList () {

        val listFilter = findViewById<EditText>(R.id.filter_list)

        listFilter.doOnTextChanged { text, _, _, _ ->

            val search = text.toString()

            if(search.isNotBlank()) {

                val filteredList = adapter.filterList(search,dao.getProducts())

                adapter.update(filteredList)

            } else {
                adapter.update(dao.getProducts())
            }

        }

    }

}