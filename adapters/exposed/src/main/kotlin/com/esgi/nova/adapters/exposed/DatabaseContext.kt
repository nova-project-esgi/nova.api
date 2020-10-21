package com.esgi.nova.adapters.exposed

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
import java.sql.SQLException

class DatabaseContext(
    private val databaseUrl: String,
    private val databaseDriver: String,
    private val databaseUsr: String,
    private val databasePwd: String
) {

    fun <T> connectAndExec(statement: Transaction.() -> T): T {
        return transaction(Connection.TRANSACTION_SERIALIZABLE, 2, connect(), statement)
    }

    fun connect() = Database.connect(
        databaseUrl, databaseDriver,
        user = databaseUsr, password = databasePwd
    )
    fun <T>transactionCommitAndCatch(statement: Transaction.()->T) = transaction {
        try {
            statement()
            commit()
        } catch (e: SQLException) {
            println(e.message)
        }
    }

}