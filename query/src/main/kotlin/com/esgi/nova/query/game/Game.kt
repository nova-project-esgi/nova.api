package com.esgi.nova.query.game

import com.esgi.nova.core_api.games.views.GameView
import com.esgi.nova.query.difficulty.Difficulty
import com.esgi.nova.query.game_event.GameEvent
import com.esgi.nova.query.game_resource.GameResource
import com.esgi.nova.query.user.User
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "game")
class Game(
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    var id: UUID,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "user_id", columnDefinition = "uniqueidentifier", nullable = false)
    var user: User,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "difficulty_id", columnDefinition = "uniqueidentifier", nullable = false)
    var difficulty: Difficulty,
    @Column(name = "is_ended")
    var isEnded: Boolean = false
) {

    @Column(name="duration")
    var duration: Int = 0

    @OneToMany(mappedBy = "game",cascade = [CascadeType.ALL])
    var gameResources: MutableList<GameResource> = mutableListOf()

    @OneToMany(mappedBy = "game",cascade = [CascadeType.ALL])
    var gameEvents: MutableList<GameEvent> = mutableListOf()

    fun toGameView() = GameView(
        id = id,
        duration = duration,
        userId = user.id,
        difficultyId = difficulty.id,
        resourceIds = gameResources.map { it.id.resourceId },
        eventIds= gameEvents.map{it.event.id},
        isEnded = isEnded
    )

}