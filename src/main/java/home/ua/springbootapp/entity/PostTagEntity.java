package home.ua.springbootapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="post_tag")

public class PostTagEntity extends BaseEntity {
    @ManyToOne
@JoinColumn(name = "post",nullable  = false)
    PostEntity post;
    @ManyToOne

@JoinColumn(name = "tag",nullable  = false)
    TagEntity tag;

}
