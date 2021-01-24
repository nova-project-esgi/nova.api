package com.esgi.nova.query.language

import com.esgi.nova.common.StringLength
import com.esgi.nova.core_api.languages.views.LanguageView
import com.esgi.nova.core_api.languages.views.LanguageViewWithAvailableActions
import com.esgi.nova.query.choice_translation.ChoiceTranslation
import com.esgi.nova.query.difficulty_translation.DifficultyTranslation
import com.esgi.nova.query.event_translation.EventTranslation
import com.esgi.nova.query.resource_translation.ResourceTranslation
import org.hibernate.annotations.Formula
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "language",
    uniqueConstraints = [UniqueConstraint(columnNames = ["code", "sub_code"])]
)
class Language(
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    var id: UUID,

    @Column(length = StringLength.SHORT_STRING)
    var code: String,
    @Column(length = StringLength.SHORT_STRING, name = "sub_code")
    var subCode: String,
    @Column(name = "is_default")
    var isDefault: Boolean = false
) {

    @OneToMany(mappedBy = "language", cascade = [CascadeType.ALL])
    var choiceTranslations: MutableList<ChoiceTranslation> = mutableListOf()

    @OneToMany(mappedBy = "language", cascade = [CascadeType.ALL])
    var eventTranslations: MutableList<EventTranslation> = mutableListOf()

    @OneToMany(mappedBy = "language", cascade = [CascadeType.ALL])
    var resourceTranslations: MutableList<ResourceTranslation> = mutableListOf()

    @OneToMany(mappedBy = "language", cascade = [CascadeType.ALL])
    var difficultyTranslations: MutableList<DifficultyTranslation> = mutableListOf()

    @Formula(value = "concat(code, '-', sub_code)")
    val concatenatedCodes =  "${code}-${subCode}"

    fun toLanguageView() = LanguageView(
        id = id,
        code = code,
        subCode = subCode,
        isDefault = isDefault
    )

    fun toLanguageViewWithAvailableActions(canSetDefault: Boolean = false) = LanguageViewWithAvailableActions(
        id = id,
        code = code,
        subCode = subCode,
        canDelete = resourceTranslations.all { translation ->
            translation.resource.resourceTranslations.size > 1
        } && eventTranslations.all { translation ->
            translation.event.eventTranslations.size > 1
        } && choiceTranslations.all { translation ->
            translation.choice.choiceTranslations.size > 1
        } && difficultyTranslations.all { translation ->
            translation.difficulty.difficultyTranslations.size > 1
        },
        isDefault = isDefault,
        canSetDefault = canSetDefault
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Language) return false

        if (id != other.id) return false
        if (code != other.code) return false
        if (subCode != other.subCode) return false
        if (isDefault != other.isDefault) return false
        if (choiceTranslations != other.choiceTranslations) return false
        if (eventTranslations != other.eventTranslations) return false
        if (resourceTranslations != other.resourceTranslations) return false
        if (difficultyTranslations != other.difficultyTranslations) return false
        if (concatenatedCodes != other.concatenatedCodes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + code.hashCode()
        result = 31 * result + (subCode?.hashCode() ?: 0)
        result = 31 * result + isDefault.hashCode()
        result = 31 * result + choiceTranslations.hashCode()
        result = 31 * result + eventTranslations.hashCode()
        result = 31 * result + resourceTranslations.hashCode()
        result = 31 * result + difficultyTranslations.hashCode()
        result = 31 * result + concatenatedCodes.hashCode()
        return result
    }


}