package com.mwaibanda.momentum.data.db

import com.squareup.sqldelight.db.SqlDriver
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}


