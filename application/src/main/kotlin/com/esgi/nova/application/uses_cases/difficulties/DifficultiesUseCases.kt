package com.esgi.nova.application.uses_cases.difficulties

import com.esgi.nova.application.axon.queryPage
import com.esgi.nova.application.uses_cases.difficulties.models.DetailedDifficultyForEdition
import com.esgi.nova.application.uses_cases.languages.LanguagesUseCases
import com.esgi.nova.core_api.difficulty.commands.*
import com.esgi.nova.core_api.difficulty.queries.*
import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyView
import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyWithAvailableActionsView
import com.esgi.nova.core_api.difficulty.views.DifficultyTranslationNameView
import com.esgi.nova.core_api.difficulty.views.TranslatedDifficultyView
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.languages.exceptions.LanguagesNotFoundByIdsException
import com.esgi.nova.core_api.languages.queries.AllLanguagesExistsByIdsQuery
import com.esgi.nova.core_api.pagination.PageBase
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
class DifficultiesUseCases(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val languagesUseCases: LanguagesUseCases
) {
    fun createDetailedDifficulty(difficulty: DetailedDifficultyForEdition): UUID {
        validaDetailedDifficultyForEdition(difficulty)
        commandGateway.sendAndWait<DifficultyIdentifier>(
            CreateDifficultyCommand(
                difficultyId = DifficultyIdentifier(),
                rank = difficulty.rank,
                isDefault = difficulty.isDefault
            )
        ).let { id ->
            difficulty.translations.forEach { translation ->
                commandGateway.sendAndWait<LanguageIdentifier>(
                    CreateDifficultyTranslationCommand(
                        difficultyId = id,
                        translationId = LanguageIdentifier(translation.languageId.toString()),
                        name = translation.name
                    )
                )
            }
            return id.toUUID();
        }
    }

    fun updateDetailedDifficulty(id: UUID, difficulty: DetailedDifficultyForEdition) {
        validaDetailedDifficultyForEdition(difficulty)
        val difficultyId = DifficultyIdentifier(id.toString())
        commandGateway.sendAndWait<DifficultyIdentifier>(
            UpdateDifficultyCommand(
                difficultyId = difficultyId,
                rank = difficulty.rank,
                isDefault = difficulty.isDefault,
                translations = difficulty.translations.map {
                    DifficultyTranslationEditionDto(
                        difficultyId = difficultyId,
                        name = it.name,
                        translationId = LanguageIdentifier(it.languageId.toString())
                    )
                }
            )
        )
    }


    fun deleteOneById(id: UUID) {
        this.commandGateway
            .sendAndWait<Any>(DeleteDifficultyCommand(difficultyId = DifficultyIdentifier(id.toString())))
    }


    private fun validaDetailedDifficultyForEdition(difficulty: DetailedDifficultyForEdition) {
        val languageIds = difficulty.translations.map { translation -> translation.languageId };
        val defaultLanguageId = languagesUseCases.getDefaultLanguageId()
        if (languageIds.none { it == defaultLanguageId }) {
            throw NoDefaultLanguageDifficultyTranslation()
        }
        val languagesExists = queryGateway.query(
            AllLanguagesExistsByIdsQuery(languageIds = languageIds.map { LanguageIdentifier(it.toString()) }),
            Boolean::class.java
        ).join()
        if (!languagesExists) {
            throw LanguagesNotFoundByIdsException(languageIds)
        }
    }

    fun getPaginatedDetailedDifficulty(
        page: Int,
        size: Int,
        name: String,
        language: String
    ): PageBase<DetailedDifficultyWithAvailableActionsView> {
        return queryGateway.queryPage<DetailedDifficultyWithAvailableActionsView, FindPaginatedDifficultyWithAvailableActionsViewsByConcatenatedCodesAndNameQuery>(
            FindPaginatedDifficultyWithAvailableActionsViewsByConcatenatedCodesAndNameQuery(
                concatenatedCodes = language,
                name = name,
                page = page,
                size = size
            )
        ).join()
    }


    fun getPaginatedDifficultyNamesByNameLanguageCodeAndSubCode(
        page: Int,
        size: Int,
        name: String,
        language: String
    ): PageBase<DifficultyTranslationNameView> {
        return queryGateway.queryPage<DifficultyTranslationNameView, FindPaginatedDifficultyTranslationNameViewsByNameAndConcatenatedCodeQuery>(
            FindPaginatedDifficultyTranslationNameViewsByNameAndConcatenatedCodeQuery(
                name = name,
                concatenatedCode = language,
                page = page,
                size = size
            )
        ).join()
    }

    fun findOneDetailedDifficultyWithActionsById(id: UUID): DetailedDifficultyWithAvailableActionsView {
        return queryGateway.query<DetailedDifficultyWithAvailableActionsView?, FindOneDifficultyWithAvailableActionsViewQuery>(
            FindOneDifficultyWithAvailableActionsViewQuery(DifficultyIdentifier(id.toString()))
        ).join() ?: throw DifficultyNotFoundByIdException()
    }

    fun getAllPaginated(page: Int, size: Int): PageBase<DetailedDifficultyView> {
        return queryGateway.queryPage<DetailedDifficultyView, FindPaginatedDifficultyViewsQuery>(
            FindPaginatedDifficultyViewsQuery(
                page, size
            )
        ).join()
    }

    fun loadAllStandardDifficultiesByLanguage(language: String): List<TranslatedDifficultyView> {
        return queryGateway.queryMany<TranslatedDifficultyView, FindAllTranslatedDifficultiesByLanguageQuery>(
            FindAllTranslatedDifficultiesByLanguageQuery(language)
        ).join()
    }

}