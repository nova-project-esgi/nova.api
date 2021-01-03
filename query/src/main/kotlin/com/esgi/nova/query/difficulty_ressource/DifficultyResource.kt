package com.esgi.nova.query.difficulty_ressource

import com.esgi.nova.core_api.difficulty.views.DifficultyResourceView
import com.esgi.nova.core_api.resources.views.ResourceDifficultyView
import com.esgi.nova.query.difficulty.Difficulty
import com.esgi.nova.query.resource.Resource
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "difficulty_resource")
class DifficultyResource(
    @EmbeddedId
    var id: DifficultyResourceId,
    @Column(name = "start_value")
    var startValue: Int,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "difficulty_id", columnDefinition = "uniqueidentifier", insertable = false, updatable = false)
    @MapsId("difficultyId")
    var difficulty: Difficulty,
    @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "resource_id", columnDefinition = "uniqueidentifier", insertable = false, updatable = false)
    @MapsId("resourceId")
    var resource: Resource
) {

    fun toResourceDifficultyView() = ResourceDifficultyView(
        resourceId = resource.id,
        startValue = startValue,
        difficulty = difficulty.toDetailedDifficultyView()
    )

    fun toDifficultyResourceView() = DifficultyResourceView(
        resourceId = resource.id,
        startValue = startValue
    )
}

