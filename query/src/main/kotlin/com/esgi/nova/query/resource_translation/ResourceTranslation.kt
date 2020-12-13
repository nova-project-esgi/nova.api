package com.esgi.nova.query.resource_translation

import com.esgi.nova.common.StringLength
import com.esgi.nova.core_api.resources.views.ResourceTranslationView
import com.esgi.nova.core_api.resources.views.TranslatedResourceView
import com.esgi.nova.query.language.Language
import com.esgi.nova.query.resource.Resource
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "resource_translation")
class ResourceTranslation {
    @EmbeddedId
    lateinit var id: ResourceTranslationId

    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "resource_id", columnDefinition = "uniqueidentifier")
    @MapsId("resourceId")
    lateinit var resource: Resource

    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "language_id", columnDefinition = "uniqueidentifier")
    @MapsId("languageId")
    lateinit var language: Language

    @Column(length = StringLength.MEDIUM_STRING)
    lateinit var name: String

    fun toTranslatedResourceView(): TranslatedResourceView =
        TranslatedResourceView(
            id = resource.id,
            languageIds = resource.resourceTranslations.map { it.id.languageId },
            name = name
        )

    fun toResourceTranslationView(): ResourceTranslationView = ResourceTranslationView(
        resourceId = resource.id,
        languageId = language.id,
        name = name
    )
}