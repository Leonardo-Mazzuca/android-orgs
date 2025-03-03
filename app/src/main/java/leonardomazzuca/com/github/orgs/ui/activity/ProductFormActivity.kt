package leonardomazzuca.com.github.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import leonardomazzuca.com.github.orgs.R
import leonardomazzuca.com.github.orgs.dao.ProductsDAO
import leonardomazzuca.com.github.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(
    R.layout.activity_product_form
) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val btnSave = findViewById<Button>(R.id.saveBtn)

        btnSave.setOnClickListener {
            configSaveBtn()
        }

    }

    private fun configSaveBtn() {

        val productDao = ProductsDAO()
        val product = createProduct()
        productDao.add(product)
        finish()

    }

    private fun createProduct(): Product {
        val name = findViewById<EditText>(R.id.form_product_item_name).text.toString()
        val desc = findViewById<EditText>(R.id.form_product_item_description).text.toString()
        val textPrice = findViewById<EditText>(R.id.form_product_item_price).text.toString()

        val price = if (textPrice.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(textPrice)
        }

        return Product(
            name = name,
            description = desc,
            price = price
        )

    }


}

