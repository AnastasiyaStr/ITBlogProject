package home.ua.springbootapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="posts")
public class PostEntity extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "post_created", nullable=false)
    private String postCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPostCreated() {
        return postCreated;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPostCreated(String postCreated) {
        this.postCreated = postCreated;
    }
}
