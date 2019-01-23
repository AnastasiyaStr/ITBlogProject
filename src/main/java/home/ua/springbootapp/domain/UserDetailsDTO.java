package home.ua.springbootapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsDTO {
    private Long id;

    private String email;


    private String dateOfBirth;


    private String maritalStatus;


    private String hobby;


    private String technologies;

    private UserDTO user;
}
