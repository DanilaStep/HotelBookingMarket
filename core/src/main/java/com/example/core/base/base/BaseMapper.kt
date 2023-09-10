package com.example.core.base

interface BaseMapper<R, M> {
    fun R.map():M
}