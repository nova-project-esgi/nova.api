package com.esgi.nova.query.language

import com.esgi.nova.common.StringLength
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.core_api.languages.queries.views.LanguageViewWithAvailableActions
import com.esgi.nova.query.choice_translation.ChoiceTranslation
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
    var code: String, @Column(length = StringLength.SHORT_STRING, name = "sub_code") var subCode: String? = null
) {

    @OneToMany(mappedBy = "language", cascade = [CascadeType.ALL])
    var choiceTranslations: MutableList<ChoiceTranslation> = mutableListOf()

    @OneToMany(mappedBy = "language", cascade = [CascadeType.ALL])
    var eventTranslations: MutableList<EventTranslation> = mutableListOf()

    @OneToMany(mappedBy = "language", cascade = [CascadeType.ALL])
    var resourceTranslations: MutableList<ResourceTranslation> = mutableListOf()


    @Formula(value = "concat(code, '-', sub_code)")
    val concatenatedCodes = if (subCode != null) "${code}-${subCode}" else code

    fun toLanguageView() = LanguageView(
        id = id,
        code = code,
        subCode = subCode
    )
    fun toLanguageViewWithAvailableActions() = LanguageViewWithAvailableActions(
        id = id,
        code = code,
        subCode = subCode,
        canDelete = resourceTranslations.all { translation ->
            translation.resource.resourceTranslations.size > 1
        }
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Language

        if (id != other.id) return false
        if (code != other.code) return false
        if (subCode != other.subCode) return false
        if (choiceTranslations != other.choiceTranslations) return false
        if (eventTranslations != other.eventTranslations) return false
        if (resourceTranslations != other.resourceTranslations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + code.hashCode()
        result = 31 * result + (subCode?.hashCode() ?: 0)
        result = 31 * result + choiceTranslations.hashCode()
        result = 31 * result + eventTranslations.hashCode()
        result = 31 * result + resourceTranslations.hashCode()
        return result
    }


}