package com.esgi.nova.query.resource

import com.esgi.nova.query.choice_resource.ChoiceResource
import com.esgi.nova.query.resource_translation.ResourceTranslation
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "resource")
class Resource {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    lateinit var id: UUID

    @OneToMany(mappedBy = "resource")
    lateinit var choiceResources: Collection<ChoiceResource>

    @OneToMany(mappedBy = "resource")
    lateinit var resourceTranslations: Collection<ResourceTranslation>

}