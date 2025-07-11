package com.butterknifebudgeting.app.plugins

import com.butterknifebudgeting.app.DatabaseManager
import com.butterknifebudgeting.app.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(databaseManager: DatabaseManager) {
    val userRepository = UserRepository(databaseManager)

    routing {
        route("/api") {
            route("/income") {
                get {
                    try {
                        val users = userRepository.getAllUsers()
                        call.respond("Hello world")
                    } catch (e: Exception) {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                        )
                    }
                }
            }
        }
    }
}