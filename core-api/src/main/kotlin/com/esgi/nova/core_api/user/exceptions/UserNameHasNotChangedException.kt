package com.esgi.nova.core.domain.users.exceptions

class UserNameHasNotChangedException(name: String) : IllegalArgumentException("User name is not different: $name")
