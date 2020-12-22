package com.esgi.nova.core_api.user.events

import com.esgi.nova.core_api.user.UserIdentifier
import java.io.Serializable

data class UserDeleteEvent(val userId: UserIdentifier) : Serializable {

}
