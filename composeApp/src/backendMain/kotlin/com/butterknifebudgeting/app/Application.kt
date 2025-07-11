package com.butterknifebudgeting.app

import com.butterknifebudgeting.app.plugins.configureHTTP
import com.butterknifebudgeting.app.plugins.configureMonitoring
import com.butterknifebudgeting.app.plugins.configureRouting
import com.butterknifebudgeting.app.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStopping
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // Initialize database
    val databaseManager = DatabaseManager()
    databaseManager.initDatabase()

    // Configure plugins
    configureSerialization()
    configureHTTP()
    configureMonitoring()

    // Configure routing
    configureRouting(databaseManager)

    // Cleanup on shutdown
    environment.monitor.subscribe(ApplicationStopping) {
        databaseManager.closeDatabase()
    }
}