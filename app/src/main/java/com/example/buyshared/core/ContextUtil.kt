package com.example.buyshared.core

import android.content.Context
import android.content.ContextParams

class ContextUtil {
    lateinit var contextSaved : Context

    public fun setContext(context: Context):Context{
        this.contextSaved = context
        return context
    }

    public fun getContextSaved1():Context{
        return this.contextSaved
    }
}