package home.ua.springbootapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;


    private String firstName;


    private String lastName;


    private String nic;


    private String accountCreated;
}
