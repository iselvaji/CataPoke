package com.cw.catapoke.util

import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class DestinationsNavigatorImpl : DestinationsNavigator {
    override fun clearBackStack(route: String): Boolean {
        return true
    }

    override fun navigate(
        route: String,
        onlyIfResumed: Boolean,
        builder: NavOptionsBuilder.() -> Unit
    ) {

    }

    override fun navigateUp(): Boolean {
       return true
    }

    override fun popBackStack(): Boolean {
        return true
    }

    override fun popBackStack(route: String, inclusive: Boolean, saveState: Boolean): Boolean {
        return true
    }
}