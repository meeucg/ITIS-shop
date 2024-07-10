package com.example.itis_shop.storage

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.runBlocking

val user_id = "OoGXzfzrdUc3Fe5WAqT7"

class Storage {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var catalog: MutableList<Product> = mutableListOf()
    var favorites: MutableList<Product> = mutableListOf()
    var shoppingCart: MutableMap<Product, Int> = mutableMapOf()
    var shoppingCartTotalPrice: Int = 0
    var userData: UserData = UserData()

    init{
        addCatalogReadOnCompleteListener(
            action = {
                catalog = it
                readUserData(id = user_id,
                    onEnd = {
                        userData = it
                        for(productId in userData.favorites){
                            catalog.find { it.id == productId }
                                .let { product ->
                                    if(product != null){
                                        favorites.add(product)
                                    }
                                }
                        }
                        for(pair in userData.shoppingCart){
                            catalog.find { it.id == pair.key }
                                .let { product ->
                                    if(product != null){
                                        shoppingCart.put(product, pair.value)
                                        shoppingCartTotalPrice += product.price * pair.value
                                    }
                                }
                        }
                    })

            },
            error = {}
        )
    }

    fun addCatalogReadOnCompleteListener(action: (MutableList<Product>) -> Unit, error: () -> Unit){
        db.collection(CATALOG_PATH).get().addOnCompleteListener{
            if(it.isSuccessful) {
                action(it.result.toObjects(Product::class.java))
            } else {
                error()
            }
        }
    }

    fun addToCatalog(product: Product,
                     onFail: ()->Unit = { },
                     onStart: ()->Unit = { },
                     onEnd: ()->Unit = { }) {
        runBlocking {
            launch {
                onStart()
                for(p in catalog){
                    if(p.id == product.id){
                        onFail()
                        return@launch
                    }
                }

                db.collection(CATALOG_PATH)
                    .document(product.id)
                    .set(product).addOnCompleteListener {
                        if(it.isSuccessful) {
                            catalog.add(product)
                            onEnd()
                        } else{
                            onFail()
                        }
                    }
            }
        }
    }

    fun removeFromCatalogById(id: String,
                              onFail: ()->Unit = { },
                              onStart: ()->Unit = { },
                              onEnd: ()->Unit = { }){
        runBlocking {
            launch {
                onStart()
                for(p in catalog){
                    if(p.id == id){
                        db.collection(CATALOG_PATH)
                            .document(id)
                            .delete().addOnCompleteListener {
                                if(it.isSuccessful) {
                                    catalog.remove(p)
                                    onEnd()
                                } else{
                                    onFail()
                                }
                            }
                        return@launch
                    }
                }
                onFail()
            }
        }
    }

    fun readCatalogById(id: String,
                        onFail: ()->Unit = { },
                        onStart: ()->Unit = { },
                        onEnd: (Product)->Unit = { })
    {
        runBlocking {
            launch {
                onStart()
                db.collection(CATALOG_PATH)
                    .document(id).get().addOnCompleteListener{
                        if (it.isSuccessful)
                        {
                            onEnd(it.result.toObject(Product::class.java) ?: Product())
                        } else {
                            onFail()
                        }
                    }
            }
        }
    }

    /* TODO: editProductById includes changing id in a class itself and in local
       TODO: and cloud storage */

    fun readUserData(id: String,
                    onFail: ()->Unit = { },
                    onStart: ()->Unit = { },
                    onEnd: (UserData)->Unit = { }){
        runBlocking {
            launch {
                onStart()
                db.collection(USER_DATA_PATH)
                    .document(id).get().addOnCompleteListener{
                        if (it.isSuccessful)
                        {
                            onEnd(it.result.toObject(UserData::class.java) ?: UserData())
                        } else {
                            onFail()
                        }
                    }
            }
        }
    }

    fun updateBalance(userId: String,
                      newBalance: Int,
                      onFail: ()->Unit = { },
                      onStart: ()->Unit = { },
                      onEnd: ()->Unit = { }){
        runBlocking {
            launch {
                onStart()
                db.collection(USER_DATA_PATH)
                    .document(userId)
                    .update("balance", newBalance)
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            userData.balance = newBalance
                            onEnd()
                        } else {
                            onFail()
                        }
                    }
            }
        }
    }

    fun addToFavorite(userId: String,
                      productId: String,
                      onFail: ()->Unit = { },
                      onStart: ()->Unit = { },
                      onEnd: ()->Unit = { }){
        runBlocking {
            launch {
                onStart()
                db.collection(USER_DATA_PATH)
                    .document(userId)
                    .update("favorites", FieldValue.arrayUnion(productId))
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            if(!userData.favorites.contains(productId)){
                                catalog.find { p -> p.id == productId }
                                    .let { product ->
                                        if(product != null){
                                            favorites.add(product)
                                        }
                                    }
                                userData.favorites.add(productId)
                                onEnd()
                            } else {
                                onFail()
                            }
                        } else {
                            onFail()
                        }
                    }
            }
        }
    }

    fun removeFromFavorite(userId: String,
                      productId: String,
                      onFail: ()->Unit = { },
                      onStart: ()->Unit = { },
                      onEnd: ()->Unit = { }){
        runBlocking {
            launch {
                onStart()
                db.collection(USER_DATA_PATH)
                    .document(userId)
                    .update("favorites", FieldValue.arrayRemove(productId))
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            catalog.find { it.id == productId }
                                .let{ product ->
                                    if(product!=null){
                                        favorites.remove(product)
                                    }
                                }
                            userData.favorites.remove(productId)
                            onEnd()
                        } else {
                            onFail()
                        }
                    }
            }
        }
    }

    fun addToShoppingCart(userId: String,
                          productId: String,
                          count: Int,
                          onFail: ()->Unit = { },
                          onStart: ()->Unit = { },
                          onEnd: ()->Unit = { }){
        runBlocking {
            launch {
                onStart()
                db.collection(USER_DATA_PATH)
                    .document(userId)
                    .update(mapOf("shoppingCart.${productId}" to count))
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            userData.shoppingCart.put(productId, count)
                            val product = catalog.find {
                                it.id == productId
                            } ?: return@addOnCompleteListener
                            shoppingCartTotalPrice += product.price * count
                            shoppingCart.put(product, count)
                            onEnd()
                        } else {
                            onFail()
                        }
                    }
            }
        }
    }

    fun removeFromShoppingCart(userId: String,
                               productId: String,
                               onFail: ()->Unit = { },
                               onStart: ()->Unit = { },
                               onEnd: ()->Unit = { }){
        runBlocking {
            launch {
                onStart()
                db.collection(USER_DATA_PATH)
                    .document(userId)
                    .update("shoppingCart.${productId}", FieldValue.delete())
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            userData.shoppingCart.remove(productId)
                            val product = shoppingCart.keys.find {
                                it.id == productId
                            }
                            shoppingCartTotalPrice -= product!!.price * shoppingCart[product]!!
                            shoppingCart.remove(product)
                            onEnd()
                        } else {
                            onFail()
                        }
                    }
            }
        }
    }

    fun incrementCountByCountShoppingCart(userId: String,
                                          productId: String,
                                          count: Int,
                                          onFail: ()->Unit = { },
                                          onStart: ()->Unit = { },
                                          onEnd: ()->Unit = { }){
        runBlocking {
            launch {
                onStart()
                db.collection(USER_DATA_PATH)
                    .document(userId)
                    .update("shoppingCart.${productId}",
                        FieldValue.increment(count.toLong()))
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            userData.shoppingCart[productId] =
                                userData.shoppingCart[productId]!! + count
                            val product = catalog.find {
                                it.id == productId
                            }!!
                            shoppingCart[product] = shoppingCart[product]!! + count
                            shoppingCartTotalPrice += product.price * count
                            onEnd()
                        } else {
                            onFail()
                        }
                    }
            }
        }
    }

    companion object{
        private const val CATALOG_PATH = "catalog"
        private const val USER_DATA_PATH = "user_data"
    }
}