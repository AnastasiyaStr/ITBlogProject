package home.ua.springbootapp.domain;

import home.ua.springbootapp.entity.CommentEntity;
import home.ua.springbootapp.entity.PostEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Setter
@Getter
@NoArgsConstructor
public class CommentPostDTO {
    Long id;

    CommentDTO comment;

    PostDTO post;
}
