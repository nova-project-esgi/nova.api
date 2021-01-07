package com.esgi.nova.query.resource_translation

import com.esgi.nova.common.StringLength
import com.esgi.nova.core_api.resources.views.ResourceTranslationNameView
import com.esgi.nova.core_api.resources.views.ResourceTranslationView
import com.esgi.nova.core_api.resources.views.ResourceTranslationViewWithLanguage
import com.esgi.nova.query.language.Language
import com.esgi.nova.query.resource.Resource
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "resource_translation")
class ResourceTranslation(
    @EmbeddedId
    var id: ResourceTranslationId,
    @Column(length = StringLength.MEDIUM_STRING)
    var name: String,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "resource_id", columnDefinition = "uniqueidentifier", insertable = false, updatable = false)
    @MapsId("resourceId")
    var resource: Resource,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "language_id", columnDefinition = "uniqueidentifier", insertable = false, updatable = false)
    @MapsId("languageId")
    var language: Language
) {


    fun toResourceTranslationView(): ResourceTranslationView = ResourceTranslationView(
        resourceId = resource.id,
        languageId = language.id,
        name = name
    )

    fun toResourceTranslationViewWithLanguage(): ResourceTranslationViewWithLanguage =
        ResourceTranslationViewWithLanguage(
            name = name,
            language = language.toLanguageView()
        )

    fun toResourceTranslationNameView(): ResourceTranslationNameView = ResourceTranslationNameView(name = name)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceTranslation

        if (id != other.id) return false
        if (name != other.name) return false
        if (resource != other.resource) return false
        if (language != other.language) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + resource.hashCode()
        result = 31 * result + language.hashCode()
        return result
    }


}