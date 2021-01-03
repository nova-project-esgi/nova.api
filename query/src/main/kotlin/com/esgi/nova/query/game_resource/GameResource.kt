package com.esgi.nova.query.game_resource

import com.esgi.nova.query.choice.Choice
import com.esgi.nova.query.game.Game
import com.esgi.nova.query.resource.Resource
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "game_resource")
class GameResource(

    @EmbeddedId
    var id: GameResourceId,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "game_id", columnDefinition = "uniqueidentifier")
    @MapsId("gameId")
    var game: Game,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "resource_id", columnDefinition = "uniqueidentifier")
    @MapsId("resourceId")
    var resource: Resource,
    @Column(name = "total") var total: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameResource) return false

        if (id != other.id) return false
        if (game != other.game) return false
        if (resource != other.resource) return false
        if (total != other.total) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + game.hashCode()
        result = 31 * result + resource.hashCode()
        result = 31 * result + total
        return result
    }
}