package com.esgi.nova.query.event

import com.esgi.nova.query.choice.Choice
import com.esgi.nova.query.event_translation.EventTranslation
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "event")
class Event {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    lateinit var id: UUID

    @Column(name = "is_daily")
    var isDaily: Boolean = false

    @Column(name = "is_active")
    var isActive: Boolean = false

    @OneToMany(mappedBy = "event")
    lateinit var choices: Collection<Choice>

    @OneToMany(mappedBy = "event")
    lateinit var eventTranslations: Collection<EventTranslation>
}