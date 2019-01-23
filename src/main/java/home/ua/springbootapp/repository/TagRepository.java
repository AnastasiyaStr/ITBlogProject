package home.ua.springbootapp.repository;

import home.ua.springbootapp.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity,Long> {

    boolean existsById(Long id);
}
