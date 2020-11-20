package com.esgi.nova.adapters.exposed.repositories.choices_resources

import com.esgi.nova.adapters.exposed.models.*
import java.util.*

open class ChoiceResourceRepository :
        BaseChoiceResourceRepository<UUID>() {
    override fun getOne(id: UUID): ChoiceResourceEntity? =
        ChoiceResourceEntity.findById(id)
}