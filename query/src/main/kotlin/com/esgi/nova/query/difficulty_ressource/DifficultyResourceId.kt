package com.esgi.nova.query.difficulty_ressource

import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class DifficultyResourceId(
    @Type(type = "uuid-char")
    @Column(name = "difficulty_id")
    var difficultyId: UUID,
    @Type(type = "uuid-char")
    @Column(name = "resource_id")
    var resourceId: UUID
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DifficultyResourceId) return false

        if (difficultyId != other.difficultyId) return false
        if (resourceId != other.resourceId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = difficultyId.hashCode()
        result = 31 * result + resourceId.hashCode()
        return result
    }
}