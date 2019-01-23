package home.ua.springbootapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="comments")

public class CommentEntity extends BaseEntity {
    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
private String body;
}
