package home.ua.springbootapp.repository;

import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.entity.PostEntity;
import home.ua.springbootapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
            boolean existsById(Long id);
            boolean existsByTitleIgnoreCase(String title);
                boolean existsByUser(UserEntity  user);


    }

