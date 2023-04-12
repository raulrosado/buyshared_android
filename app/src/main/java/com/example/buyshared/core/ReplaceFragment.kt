package com.example.buyshared.ui.Activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class ReplaceFragment() {
    operator fun invoke(
        destino: Int,
        fragment: Fragment,
        fragmentTransaction: FragmentTransaction
    ) {
        fragmentTransaction.replace(destino, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit()
    }
}