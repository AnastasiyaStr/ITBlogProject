package home.ua.springbootapp.repository;

import home.ua.springbootapp.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    boolean existsById(Long id);
}
