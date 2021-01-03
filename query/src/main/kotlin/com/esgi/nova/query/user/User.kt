package com.esgi.nova.query.user

import com.esgi.nova.common.StringLength
import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.views.UserAdminEditView
import com.esgi.nova.core_api.user.views.UserCredential
import com.esgi.nova.core_api.user.views.UserResume
import com.esgi.nova.core_api.user.views.UserUsername
import com.esgi.nova.query.game.Game
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
open class User(
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    open var id: UUID,
    @Column(length = StringLength.LONG_STRING, unique = true) open var username: String,
    @Column(length = StringLength.LONG_STRING) open var password: String,
    @Column(length = StringLength.LONG_STRING, unique = true) open var email: String,
    @Enumerated(EnumType.STRING) open var role: Role,
    @OneToMany(mappedBy = "user") open var games: Collection<Game>
) {

    fun toUserResume(): UserResume = UserResume(
        id = id,
        email = email,
        role = role,
        username = username
    )

    fun toUserCredential(): UserCredential {
        return UserCredential(
            username = username,
            password = password
        )
    }

    fun toUserUsername(): UserUsername {
        return UserUsername(
            id = id,
            username = username
        )
    }

    fun toUserAdminEdit(canUpdateRole: Boolean): UserAdminEditView {
        return UserAdminEditView(
            id = id,
            email = email,
            role = role,
            username = username,
            canUpdateRole = canUpdateRole
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (email != other.email) return false
        if (role != other.role) return false
        if (games != other.games) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + role.hashCode()
        result = 31 * result + games.hashCode()
        return result
    }


}
