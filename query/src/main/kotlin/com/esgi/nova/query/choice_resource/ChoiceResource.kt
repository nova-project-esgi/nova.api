package com.esgi.nova.query.choice_resource

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

}