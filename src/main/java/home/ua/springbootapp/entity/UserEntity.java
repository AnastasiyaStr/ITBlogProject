package home.ua.springbootapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="users")
public class UserEntity extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String nic;

    @Column(name = "account_created", nullable=false)
    private String accountCreated;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNic() {
        return nic;
    }

    public String getAccountCreated() {
        return accountCreated;
    }
}
