package com.esgi.nova.query.game_event

import com.esgi.nova.core_api.games.views.GameEventView
import com.esgi.nova.query.event.Event
import com.esgi.nova.query.game.Game
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "game_event")
class GameEvent(
    @ManyToOne
    @JoinColumn(name = "game_id", columnDefinition = "uniqueidentifier")
    var game: Game,
    @ManyToOne
    @JoinColumn(name = "event_id", columnDefinition = "uniqueidentifier")
    var event: Event,
    @Column(name = "link_time")
    var linkTime: LocalDateTime,

    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    var id: UUID = UUID.randomUUID()
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameEvent

        if (id != other.id) return false
        if (game != other.game) return false
        if (event != other.event) return false
        if (linkTime != other.linkTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + game.hashCode()
        result = 31 * result + event.hashCode()
        result = 31 * result + linkTime.hashCode()
        return result
    }

    fun toGameEventView() = GameEventView(
        eventId = event.id,
        linkTime = linkTime
    )

}