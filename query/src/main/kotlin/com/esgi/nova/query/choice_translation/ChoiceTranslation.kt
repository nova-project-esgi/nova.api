package com.esgi.nova.query.choice_translation

import com.esgi.nova.common.StringLength
import com.esgi.nova.query.choice.Choice
import com.esgi.nova.query.language.Language
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "choice_translation")
class ChoiceTranslation {

    @EmbeddedId
    lateinit var id: ChoiceTranslationId

    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "choice_id", columnDefinition = "uniqueidentifier")
    @MapsId("choiceId")
    lateinit var choice: Choice

    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "language_id", columnDefinition = "uniqueidentifier")
    @MapsId("languageId")
    lateinit var language: Language

    @Column(length = StringLength.LONG_STRING)
    lateinit var title: String

    @Column
    lateinit var description: String
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChoiceTranslation

        if (id != other.id) return false
        if (choice != other.choice) return false
        if (language != other.language) return false
        if (title != other.title) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + choice.hashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }


}