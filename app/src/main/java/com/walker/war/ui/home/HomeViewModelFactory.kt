package com.walker.war.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.walker.war.data.MainRepository
import com.walker.war.newwork.NetworkHelper

/**
 * Created by admin on 2021/7/21.
 */
class HomeViewModelFactory(var key: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MainRepository::class.java).newInstance(key)
    }
}