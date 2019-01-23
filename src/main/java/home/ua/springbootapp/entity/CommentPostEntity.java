package home.ua.springbootapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="comment_post")

public class CommentPostEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "comment",nullable  = false)
    CommentEntity comment;

    @ManyToOne
    @JoinColumn(name = "post",nullable  = false)
    PostEntity post;

}
