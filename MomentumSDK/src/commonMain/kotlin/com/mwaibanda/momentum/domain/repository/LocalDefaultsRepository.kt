package com.mwaibanda.momentum.domain.repository

interface LocalDefaultsRepository {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}