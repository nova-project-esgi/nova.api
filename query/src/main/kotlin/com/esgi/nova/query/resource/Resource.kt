package com.esgi.nova.query.resource

import com.esgi.nova.core_api.resources.views.ResourceWithAvailableActionsView
import com.esgi.nova.core_api.resources.views.ResourceWithTranslationIdsView
import com.esgi.nova.core_api.resources.views.TranslatedResourceView
import com.esgi.nova.query.choice_resource.ChoiceResource
import com.esgi.nova.query.difficulty_ressource.DifficultyResource
import com.esgi.nova.query.game_resource.GameResource
import com.esgi.nova.query.resource_translation.ResourceTranslation
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "resource")
class Resource(
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    var id: UUID
) {

    @OneToMany(mappedBy = "resource", cascade = [CascadeType.ALL])
    var choiceResources: MutableList<ChoiceResource> = mutableListOf()

    @OneToMany(mappedBy = "resource", cascade = [CascadeType.ALL])
    var gameResources: MutableList<GameResource> = mutableListOf()

    @OneToMany(mappedBy = "resource", cascade = [CascadeType.ALL])
    var resourceTranslations: MutableList<ResourceTranslation> = mutableListOf()

    @OneToMany(mappedBy = "resource", cascade = [CascadeType.ALL])
    var resourceDifficulties: MutableList<DifficultyResource> = mutableListOf()

    fun toTranslatedResourceView(language: String): TranslatedResourceView {
        val translation = resourceTranslations.firstOrNull { t -> t.language.concatenatedCodes == language }
            ?: resourceTranslations.first { t -> t.language.isDefault }
        return TranslatedResourceView(
            id = id,
            language = translation.language.concatenatedCodes,
            name = translation.name
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Resource

        if (id != other.id) return false
        if (choiceResources != other.choiceResources) return false
        if (resourceTranslations != other.resourceTranslations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + choiceResources.hashCode()
        result = 31 * result + resourceTranslations.hashCode()
        return result
    }

    fun toResourceWithAvailableActionsView(): ResourceWithAvailableActionsView = ResourceWithAvailableActionsView(
        id = id,
        translations = resourceTranslations.map { it.toResourceTranslationViewWithLanguage() },
        canDelete = choiceResources.isEmpty(),
        difficulties = resourceDifficulties.map { it.toResourceDifficultyView() }
    )

    fun toResourceWithTranslationIdsView(): ResourceWithTranslationIdsView = ResourceWithTranslationIdsView(
        id = id,
        translationIds = resourceTranslations.map { it.id.languageId }
    )
}