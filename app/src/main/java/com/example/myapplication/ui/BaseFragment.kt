package com.example.myapplication.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


abstract class BaseFragment: Fragment {
    constructor() : super()
    constructor(@LayoutRes layoutId: Int) : super(layoutId)
}