package com.esgi.nova.query.choice_resource

import org.springframework.data.jpa.repository.JpaRepository

interface ChoiceResourceRepository : JpaRepository<ChoiceResource, ChoiceResourceId> {
}