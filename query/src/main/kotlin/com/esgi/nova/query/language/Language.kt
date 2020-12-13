package com.esgi.nova.query.language

import com.esgi.nova.common.StringLength
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.query.choice_translation.ChoiceTranslation
import com.esgi.nova.query.event_translation.EventTranslation
import com.esgi.nova.query.resource_translation.ResourceTranslation
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

    @OneToMany(mappedBy = "language")
    lateinit var choiceTranslations: Collection<ChoiceTranslation>

    @OneToMany(mappedBy = "language")
    lateinit var eventTranslations: Collection<EventTranslation>

    @OneToMany(mappedBy = "language")
    lateinit var resourceTranslations: Collection<ResourceTranslation>

    val concatenatedCodes get() = if (subCode != null) "${code}-${subCode}" else code

    fun toLanguageView() = LanguageView(
        id = id,
        code = code,
        subCode = subCode
    )
}