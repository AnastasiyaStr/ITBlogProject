package home.ua.springbootapp.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String postCreated;
    private UserDTO user;
}
