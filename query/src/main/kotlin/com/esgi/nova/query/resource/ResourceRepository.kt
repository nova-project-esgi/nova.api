package com.esgi.nova.query.resource

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ResourceRepository : JpaRepository<Resource, UUID> {
}