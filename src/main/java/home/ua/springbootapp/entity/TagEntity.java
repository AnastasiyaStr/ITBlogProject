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
@Table(name ="tags")
public class TagEntity extends BaseEntity {

        @Column(name = "tag_name", nullable = false)
        private String tagName;

}
