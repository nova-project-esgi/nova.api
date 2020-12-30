package com.esgi.nova.query.choice_translation

import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ChoiceTranslationId(
    @Type(type = "uuid-char")
    @Column(name = "choice_id", columnDefinition = "uniqueidentifier")
    var choiceId: UUID,
    @Type(type = "uuid-char")
    @Column(name = "language_id", columnDefinition = "uniqueidentifier")
    var languageId: UUID
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChoiceTranslationId

        if (choiceId != other.choiceId) return false
        if (languageId != other.languageId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = choiceId.hashCode()
        result = 31 * result + languageId.hashCode()
        return result
    }
}