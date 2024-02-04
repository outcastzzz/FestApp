package com.register.festapp.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.register.festapp.data.database.AppDatabase
import com.register.festapp.di.ViewModelKey
import com.register.festapp.domain.entities.ShopItem
import com.register.festapp.domain.entities.UserData
import com.register.festapp.domain.useCases.GetShopItemListUseCase
import com.register.festapp.domain.useCases.GetUserDataUseCase
import com.register.festapp.domain.useCases.SaveUserDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopItemListUseCase: GetShopItemListUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val database: AppDatabase
): ViewModel() {

    private val _dbData = MutableLiveData<Boolean>()
    val dbData: LiveData<Boolean> = _dbData

    private val _itemList = MutableLiveData<List<ShopItem>>()
    val itemList: LiveData<List<ShopItem>> = _itemList

    private val _favouriteList = MutableLiveData<MutableList<ShopItem>>()
    val favouriteList: LiveData<MutableList<ShopItem>> = _favouriteList

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem> = _shopItem

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData

    init {
        getList()
        checkDbExist()
    }

    private fun getList() {
        viewModelScope.launch {
            val list = getShopItemListUseCase.getShopItemList()
            _itemList.value = list
        }
    }

    fun saveUserData(data: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserDataUseCase.saveUserData(data)
        }
    }

    fun getShopItem(position: Int) {
        val item = itemList.value?.get(position)
        _shopItem.value = item!!
    }

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getUserDataUseCase.getUserData()
            withContext(Dispatchers.Main) {
                _userData.value = data
            }
        }
    }

    fun addItemToFavourites(item: ShopItem) {
        val newList = _favouriteList.value?.toMutableList() ?: mutableListOf()
        newList.add(item.copy())
        _favouriteList.value = newList
    }

    fun removeItemFromFavourites(item: ShopItem) {
        item.let {
            _favouriteList.value?.remove(it)
            _favouriteList.value = _favouriteList.value
        }
    }

    fun checkDbExist() {
        var result = false
        viewModelScope.launch(Dispatchers.IO) {
            val data = getUserDataUseCase.getUserData()
            withContext(Dispatchers.Main) {
                result = data.name != ""
                _dbData.value = result
            }
        }
    }

    fun clearDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            database.clearAllTables()
        }
    }

}