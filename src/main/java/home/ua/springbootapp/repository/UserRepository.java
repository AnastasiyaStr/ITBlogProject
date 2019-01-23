package home.ua.springbootapp.repository;

import home.ua.springbootapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
    public interface UserRepository extends JpaRepository<UserEntity, Long> {


        boolean existsById(Long id);
        boolean existsByFirstName(String firstName);
        UserEntity findByFirstName(String firstName);
        Optional<UserEntity> findById(Long id);
    }

