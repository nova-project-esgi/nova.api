package com.esgi.nova.core_api.user.queries

data class FindUserByUsernameAndPasswordQuery(val username: String, val password: String) {
}