package com.esgi.nova.query.difficulty

import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyView
import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyWithAvailableActionsView
import com.esgi.nova.core_api.difficulty.views.TranslatedDifficultyView
import com.esgi.nova.query.difficulty_ressource.DifficultyResource
import com.esgi.nova.query.difficulty_translation.DifficultyTranslation
import com.esgi.nova.query.game.Game
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "difficulty")
class Difficulty(
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    var id: UUID,
    @Column(name = "is_default")
    var isDefault: Boolean,
    @Column(name = "rank")
    var rank: Int
) {
    @OneToMany(mappedBy = "difficulty", cascade = [CascadeType.ALL])
    var difficultyResources: MutableList<DifficultyResource> = mutableListOf()

    @OneToMany(mappedBy = "difficulty", cascade = [CascadeType.ALL])
    var difficultyGames: MutableList<Game> = mutableListOf()

    @OneToMany(mappedBy = "difficulty", cascade = [CascadeType.ALL])
    var difficultyTranslations: MutableList<DifficultyTranslation> = mutableListOf()


    fun toDifficultyWithAvailableActions(canSetDefault: Boolean) = DetailedDifficultyWithAvailableActionsView(
        id = id,
        translations = difficultyTranslations.map { it.toDifficultyTranslationViewWithLanguage() },
        isDefault = isDefault,
        rank = rank,
        canDelete = difficultyGames.isEmpty() && difficultyResources.isEmpty(),
        canSetDefault = canSetDefault
    )

    fun toDetailedDifficultyView() = DetailedDifficultyView(
        id = id,
        translations = difficultyTranslations.map { it.toDifficultyTranslationViewWithLanguage() },
        isDefault = isDefault,
        rank = rank
    )

    fun toTranslatedDifficultyView(language: String): TranslatedDifficultyView {
        val translation = difficultyTranslations.firstOrNull { t -> t.language.concatenatedCodes == language }
            ?: difficultyTranslations.first { t -> t.language.isDefault }
        return TranslatedDifficultyView(
            id = id,
            language = translation.language.concatenatedCodes,
            name = translation.name,
            rank = rank,
            resources = difficultyResources.map { difficultyResource -> difficultyResource.toDifficultyResourceView() }
        )
    }
}