package home.ua.springbootapp.service;

import home.ua.springbootapp.domain.TagDTO;
import home.ua.springbootapp.domain.UserDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserDetailsService {
    void saveUserDetails(UserDetailsDTO userDetailsDTO);
    List<UserDetailsDTO> findAllUserDetails();
    UserDetailsDTO findUserDetailsById(Long id);

    UserDetailsDTO updateUserDetails(Long id, UserDetailsDTO userDetailsToUpdate);


}
