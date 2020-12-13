package com.esgi.nova.query.choice

import com.esgi.nova.core_api.choices.views.ChoiceView
import com.esgi.nova.query.choice_resource.ChoiceResource
import com.esgi.nova.query.choice_translation.ChoiceTranslation
import com.esgi.nova.query.event.Event
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "choice")
class Choice {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    lateinit var id: UUID

    @ManyToOne
    lateinit var event: Event

    @OneToMany(mappedBy = "choice")
    lateinit var choiceResources: Collection<ChoiceResource>

    @OneToMany(mappedBy = "choice")
    lateinit var choiceTranslations: Collection<ChoiceTranslation>

    fun toChoiceView() = ChoiceView(
            id = id,
            eventId = event.id,
            resourceIds = choiceResources.map { it.resource.id }
    )
}