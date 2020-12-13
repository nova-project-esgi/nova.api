package com.esgi.nova.query.choice_resource

import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ChoiceResourceId : Serializable {
    @Type(type = "uuid-char")
    @Column(name = "choice_id", columnDefinition = "uniqueidentifier")
    lateinit var choiceId: UUID

    @Type(type = "uuid-char")
    @Column(name = "resource_id", columnDefinition = "uniqueidentifier")
    lateinit var resourceId: UUID
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChoiceResourceId

        if (choiceId != other.choiceId) return false
        if (resourceId != other.resourceId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = choiceId.hashCode()
        result = 31 * result + resourceId.hashCode()
        return result
    }


}