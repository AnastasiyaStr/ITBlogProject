package home.ua.springbootapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String body;
}
