package home.ua.springbootapp.repository;

import home.ua.springbootapp.entity.CommentPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentPostRepository extends JpaRepository<CommentPostEntity,Long> {
    boolean existsById(Long id);
}
