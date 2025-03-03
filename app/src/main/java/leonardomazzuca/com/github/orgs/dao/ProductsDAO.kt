package leonardomazzuca.com.github.orgs.dao

import leonardomazzuca.com.github.orgs.model.Product

class ProductsDAO {

    fun add (p: Product) {

        products.add(p)

    }

    fun getProducts ():List<Product> {

        return products.toList()

    }

    companion object {
        private val products = mutableListOf<Product>()
    }

}