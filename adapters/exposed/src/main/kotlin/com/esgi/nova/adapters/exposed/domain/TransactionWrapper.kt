package com.esgi.nova.adapters.exposed.domain

import com.esgi.nova.adapters.exposed.DatabaseContext

class TransactionWrapper<T>(private val dbContext: DatabaseContext, private val initialStatement: () -> T) {

    fun <U> exec(conversionExpression: (st: T?) -> U): U {
        return dbContext.connectAndExec {
            conversionExpression(initialStatement())
        }
    }
}