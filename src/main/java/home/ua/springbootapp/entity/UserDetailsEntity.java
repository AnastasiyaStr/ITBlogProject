package home.ua.springbootapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="user_details")
public class UserDetailsEntity  extends BaseEntity {
    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name= "marital_status")
    private String maritalStatus;

    @Column(name = "hobby", nullable = false)
    private String hobby;

    @Column(name = "technologies", nullable  = false)
    private String technologies;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
