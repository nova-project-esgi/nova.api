package com.esgi.nova.ports.provided.dtos.game

import java.time.LocalDateTime

interface IGame {
    var startDate: LocalDateTime
    var score: Int
}