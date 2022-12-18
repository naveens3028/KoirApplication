package com.management.hotelapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.management.hotelapplication.database.AppDatabase
import com.management.hotelapplication.model.DataModel
import com.management.hotelapplication.model.MenuModel
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.cio.*
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {


    fun addDatasToDb(db: AppDatabase, ktorClient: HttpClient) {
        viewModelScope.launch {
            println(ktorClient.get<List<DataModel>>("https://gorest.co.in/public/v2/users"))
        }

        var dataList = ArrayList<MenuModel>()
        dataList.apply {
            add(MenuModel(itemName = "Dosa", description =  "Plain dosa", image = "https://reqres.in/img/faces/7-image.jpg", price = 40))
            add(MenuModel(itemName = "Idly", description =  "Idly sambar", image = "https://reqres.in/img/faces/7-image.jpg", price = 20))
            add(MenuModel(itemName = "Noodles", description =  "Veg noodles", image = "https://reqres.in/img/faces/7-image.jpg", price = 80))
        }.also {
            db.menuDao().updateData(it)
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}