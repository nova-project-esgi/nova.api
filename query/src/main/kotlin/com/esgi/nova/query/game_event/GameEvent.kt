package com.esgi.nova.query.game_event

import com.esgi.nova.query.event.Event
import com.esgi.nova.query.game.Game
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "game_event")
class GameEvent {
    @Id
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    lateinit var id: UUID

    @ManyToOne
    @JoinColumn(name = "game_id", columnDefinition = "uniqueidentifier")
    lateinit var game: Game

    @ManyToOne
    @JoinColumn(name = "event_id", columnDefinition = "uniqueidentifier")
    lateinit var event: Event

    @Column(name = "link_time")
    lateinit var linkTime: LocalDateTime
}