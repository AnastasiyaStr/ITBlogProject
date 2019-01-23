package home.ua.springbootapp.repository;

import home.ua.springbootapp.entity.PostTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagRepository extends JpaRepository<PostTagEntity,Long> {

    boolean existsById(Long id);
}
