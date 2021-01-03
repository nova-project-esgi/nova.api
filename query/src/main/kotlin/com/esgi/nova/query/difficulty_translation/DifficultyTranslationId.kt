package com.esgi.nova.query.difficulty_translation

import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class DifficultyTranslationId(
    @Type(type = "uuid-char")
    @Column(name = "difficulty_id")
    var difficultyId: UUID,
    @Type(type = "uuid-char")
    @Column(name = "language_id")
    var languageId: UUID
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DifficultyTranslationId) return false

        if (difficultyId != other.difficultyId) return false
        if (languageId != other.languageId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = difficultyId.hashCode()
        result = 31 * result + languageId.hashCode()
        return result
    }
}