package com.esgi.nova.query.choice_resource

import com.esgi.nova.core_api.choice_resource.views.ChoiceResourceView
import com.esgi.nova.query.choice.Choice
import com.esgi.nova.query.resource.Resource
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "choice_resource")
class ChoiceResource(@EmbeddedId
                     var id: ChoiceResourceId,
                     @ManyToOne
                     @Type(type = "uuid-char")
                     @JoinColumn(name = "choice_id", columnDefinition = "uniqueidentifier")
                     @MapsId("choiceId")
                     var choice: Choice,
                     @ManyToOne
                     @Type(type = "uuid-char")
                     @JoinColumn(name = "resource_id", columnDefinition = "uniqueidentifier")
                     @MapsId("resourceId")
                     var resource: Resource,
                     @Column(name = "change_value") var changeValue: Int = 0) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChoiceResource

        if (id != other.id) return false
        if (choice != other.choice) return false
        if (resource != other.resource) return false
        if (changeValue != other.changeValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + choice.hashCode()
        result = 31 * result + resource.hashCode()
        result = 31 * result + changeValue
        return result
    }

    fun toChoiceResourceView() = ChoiceResourceView(
        choiceId = id.choiceId,
        changeValue = changeValue,
        resource = resource.toResourceWithAvailableActionsView()
    )
}