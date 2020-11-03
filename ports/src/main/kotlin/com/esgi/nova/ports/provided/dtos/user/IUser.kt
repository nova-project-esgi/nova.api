package com.esgi.nova.ports.provided.dtos.user

import com.esgi.nova.ports.provided.enums.Role

interface IUser {
    var username: String
    var password: String
    var role: Role
}