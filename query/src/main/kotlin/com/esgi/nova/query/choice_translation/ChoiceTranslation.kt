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
}