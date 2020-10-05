package mappers;

import javax.annotation.processing.Generated;
import models.UserEntity;
import nova.api.ports.provided.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-06T01:07:06+0200",
    comments = "version: 1.4.0.Final, compiler: IncrementalProcessingEnvironment from kotlin-annotation-processing-gradle-1.4.10.jar, environment: Java 11.0.7 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        String username = null;
        String password = null;

        username = user.getUsername();
        password = user.getPassword();

        UserDto userDto = new UserDto( username, password );

        return userDto;
    }
}
