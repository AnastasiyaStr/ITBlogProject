package home.ua.springbootapp.service;

import home.ua.springbootapp.domain.UserDTO;
import home.ua.springbootapp.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

        void saveUser(UserDTO user);
        List<UserDTO> findAllUsers();
        UserDTO findUserById(Long id);
        UserDTO updateUser(Long id, UserDTO UserToUpdate);

}
