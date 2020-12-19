package com.esgi.nova.query.game

import com.esgi.nova.query.user.User
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "game")
class Game {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    lateinit var id: UUID

    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "user_id", columnDefinition = "uniqueidentifier")
    lateinit var user: User
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Game

        if (id != other.id) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + user.hashCode()
        return result
    }


}