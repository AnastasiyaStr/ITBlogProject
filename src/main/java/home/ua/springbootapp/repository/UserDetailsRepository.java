package home.ua.springbootapp.repository;

import home.ua.springbootapp.entity.TagEntity;
import home.ua.springbootapp.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity,Long> {

    boolean existsById(Long id);

}
