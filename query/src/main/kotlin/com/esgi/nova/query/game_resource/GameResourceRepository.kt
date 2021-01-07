package com.esgi.nova.query.game_resource

import org.springframework.data.jpa.repository.JpaRepository

interface GameResourceRepository : JpaRepository<GameResource, GameResourceId> {
}