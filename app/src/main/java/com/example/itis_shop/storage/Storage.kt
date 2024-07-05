package com.example.itis_shop.storage

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.lang.Error

class Storage {
    private val fs = Firebase.firestore

    private val catalogPath = "catalog"
    private val userDataPath = "user_data"

    var catalog: MutableList<Product> = mutableListOf()
    var favorite: MutableList<Product> = mutableListOf()
    var shoppingCart: MutableList<Product> = mutableListOf()

    init{
        addCatalogReadOnCompleteListener(
            {
                catalog = it
            },
            {}
        )
    }

    fun addCatalogReadOnCompleteListener(action: (MutableList<Product>) -> Unit, error: () -> Unit){
        fs.collection(catalogPath).get().addOnCompleteListener{
            if(it.isSuccessful) {
                action(it.result.toObjects(Product::class.java))
            } else {
                error()
            }
        }
    }

    fun addToCatalog(product: Product) {
        fs.collection(catalogPath).
                document().set(product)
        catalog.add(product)
    }
}