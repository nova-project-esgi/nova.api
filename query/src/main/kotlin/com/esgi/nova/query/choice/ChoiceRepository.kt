package com.esgi.nova.query.choice

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChoiceRepository : JpaRepository<Choice, UUID> {
}