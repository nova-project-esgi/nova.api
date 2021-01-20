package com.esgi.nova.core_api.games.queries

import com.esgi.nova.core_api.user.UserIdentifier

data class FindAllActiveGamesIdsByUserIdQuery(val userId: UserIdentifier)