package com.mwaibanda.momentum.data.repository

import com.mwaibanda.momentum.domain.repository.CacheRepository
import io.github.reactivecircus.cache4k.Cache

class CacheRepositoryImpl(
    private val cache: Cache<String, Any>
): CacheRepository {
    override fun <T: Any> getItem(key: String): T? {
        return cache.get(key = key) as T?
    }

    override fun <T: Any> setItem(key: String, value: T) {
        cache.put(key = key, value = value)
    }
}