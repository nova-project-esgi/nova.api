package com.esgi.nova.query.difficulty_translation

import com.esgi.nova.common.StringLength
import com.esgi.nova.core_api.difficulty.views.DifficultyTranslationNameView
import com.esgi.nova.core_api.difficulty.views.DifficultyTranslationView
import com.esgi.nova.core_api.difficulty.views.DifficultyTranslationViewWithLanguage
import com.esgi.nova.query.difficulty.Difficulty
import com.esgi.nova.query.language.Language
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "difficulty_translation")
class DifficultyTranslation(
    @EmbeddedId
    var id: DifficultyTranslationId,
    @Column(length = StringLength.MEDIUM_STRING)
    var name: String,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "difficulty_id", columnDefinition = "uniqueidentifier", insertable = false, updatable = false)
    @MapsId("difficultyId")
    var difficulty: Difficulty,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "language_id", columnDefinition = "uniqueidentifier", insertable = false, updatable = false)
    @MapsId("languageId")
    var language: Language
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DifficultyTranslation) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (difficulty != other.difficulty) return false
        if (language != other.language) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + difficulty.hashCode()
        result = 31 * result + language.hashCode()
        return result
    }

    fun toDifficultyTranslationView() = DifficultyTranslationView(
        difficultyId = difficulty.id,
        name = name,
        languageId = language.id
    )

    fun toDifficultyTranslationNameView() = DifficultyTranslationNameView(
        difficultyId = difficulty.id,
        name = name
    )

    fun toDifficultyTranslationViewWithLanguage() = DifficultyTranslationViewWithLanguage(
        name = name,
        language = language.toLanguageView()
    )
}

