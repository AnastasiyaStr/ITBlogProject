package home.ua.springbootapp.domain;

import home.ua.springbootapp.entity.PostEntity;
import home.ua.springbootapp.entity.TagEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor

public class PostTagDTO {

    Long id;

    PostDTO post;

    TagDTO tag;


}
