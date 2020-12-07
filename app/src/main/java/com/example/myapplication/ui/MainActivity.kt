package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import dagger.hilt.android.AndroidEntryPoint

//Сделать список по урл с корутинами правильной архитеркутры и +
// сохранение любимых с использованием рум.+
//Открывать по клику элемент. +
//добавить хильт и навграф. +
//Добавить поиск
//добавить тесты
//сделать номера версий библиотек параметрами
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        /*
        * When creating the NavHostFragment using FragmentContainerView or if manually adding the NavHostFragment to your activity
        * via a FragmentTransaction, attempting to retrieve the NavController in onCreate() of an Activity
        *  via Navigation.findNavController(Activity, @IdRes int) will fail. You should retrieve the NavController
        * directly from the NavHostFragment instead.*/
        (supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).findNavController()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}