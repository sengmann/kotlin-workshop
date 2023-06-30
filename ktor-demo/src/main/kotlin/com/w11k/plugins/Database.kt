package com.w11k.plugins

import com.w11k.dto.Planet
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:sqlite:/home/sengmann/Projekte/tcc/workshops/kotlin-workshop/db/solar-system.db"
    )
    val planetService = PlanetService(database)

    routing {
        get("/planet") {
            call.respond(planetService.getAll())
        }
    }
}

class PlanetService(private val database: Database) {
    object PlanetTable : Table() {
        val id = integer("id").autoIncrement()
        val name = text("name")
        val description = text("description")
        val radius = double("radius")
        val gravitation = double("gravitation")
        val positionInSystem = integer("positionInSystem")

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(PlanetTable)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun getAll(): List<Planet> {
        return dbQuery {
            PlanetTable.selectAll().map {
                Planet(
                    id = it[PlanetTable.id],
                    name = it[PlanetTable.name],
                    description = it[PlanetTable.description],
                    radius = it[PlanetTable.radius],
                    gravitation = it[PlanetTable.gravitation],
                    positionInSystem = it[PlanetTable.positionInSystem]
                )
            }
        }
    }
}
