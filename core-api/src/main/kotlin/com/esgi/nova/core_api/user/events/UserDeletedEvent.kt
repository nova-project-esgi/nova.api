package com.esgi.nova.core_api.user.events

import com.esgi.nova.core_api.user.UserIdentifier
import java.io.Serializable

data class UserDeletedEvent(val userId: UserIdentifier) : Serializable {

}
