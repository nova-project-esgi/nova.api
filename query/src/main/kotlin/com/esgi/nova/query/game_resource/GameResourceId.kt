package com.esgi.nova.query.game_resource

import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
class GameResourceId(
    @Type(type = "uuid-char")
    @Column(name = "game_id", columnDefinition = "uniqueidentifier")
    var gameId: UUID,
    @Type(type = "uuid-char")
    @Column(name = "resource_id", columnDefinition = "uniqueidentifier")
    var resourceId: UUID
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameResourceId) return false

        if (gameId != other.gameId) return false
        if (resourceId != other.resourceId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gameId.hashCode()
        result = 31 * result + resourceId.hashCode()
        return result
    }
}