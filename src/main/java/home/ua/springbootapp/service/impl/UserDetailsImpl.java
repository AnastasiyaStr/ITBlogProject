package home.ua.springbootapp.service.impl;

import home.ua.springbootapp.domain.TagDTO;
import home.ua.springbootapp.domain.UserDTO;
import home.ua.springbootapp.domain.UserDetailsDTO;
import home.ua.springbootapp.entity.TagEntity;
import home.ua.springbootapp.entity.UserDetailsEntity;
import home.ua.springbootapp.entity.UserEntity;
import home.ua.springbootapp.repository.TagRepository;
import home.ua.springbootapp.repository.UserDetailsRepository;
import home.ua.springbootapp.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Override
    public void saveUserDetails(UserDetailsDTO userDetails) {
        userDetailsRepository.save(dtoToEntityMapper(userDetails));
    }

    @Override
    public List<UserDetailsDTO > findAllUserDetails() {

        return  listEntityToListDTOMapper(userDetailsRepository.findAll());
    }

    @Override
    public UserDetailsDTO findUserDetailsById(Long id) {
        boolean exists = userDetailsRepository.existsById(id);
        if(!exists)return null;
        UserDetailsDTO userDetails =entityToDTOMapper(userDetailsRepository.findById(id).get());
        return userDetails;
    }


    @Override
    public UserDetailsDTO updateUserDetails(Long id, UserDetailsDTO  userDetailsToUpdate) {
        boolean exists = userDetailsRepository.existsById(id);
        if(!exists)return null;
        UserDetailsEntity userDetailsFromDB = userDetailsRepository.findById(id).get();

        userDetailsFromDB.setDateOfBirth(userDetailsToUpdate.getDateOfBirth());
        userDetailsFromDB.setEmail(userDetailsToUpdate.getEmail());
        userDetailsFromDB.setHobby(userDetailsToUpdate.getHobby());

        userDetailsFromDB.setMaritalStatus(userDetailsToUpdate.getMaritalStatus());
        userDetailsFromDB.setTechnologies(userDetailsToUpdate.getTechnologies());

        UserEntity userEntity = new UserEntity();
        UserDTO userDTO =userDetailsToUpdate.getUser();
        userEntity.setId(userDTO.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setNic(userDTO.getNic());
        userEntity.setAccountCreated(userDTO.getAccountCreated());
        userDetailsFromDB.setUser(userEntity);
        userDetailsRepository.save(userDetailsFromDB);
        return entityToDTOMapper(userDetailsFromDB);

    }


    public UserDetailsEntity dtoToEntityMapper(UserDetailsDTO userDetailsDTO){
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity.setDateOfBirth(userDetailsDTO.getDateOfBirth());
        userDetailsEntity.setId(userDetailsDTO.getId());
        userDetailsEntity.setEmail(userDetailsDTO.getEmail());
        userDetailsEntity.setHobby(userDetailsDTO.getHobby());
        userDetailsEntity.setMaritalStatus(userDetailsDTO.getMaritalStatus());
        userDetailsEntity.setTechnologies(userDetailsDTO.getTechnologies());
         UserEntity userEntity = new UserEntity();
         UserDTO userDTO = userDetailsDTO.getUser();
        userEntity.setId(userDTO.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setNic(userDTO.getNic());
        userEntity.setAccountCreated(userDTO.getAccountCreated());
       userDetailsEntity.setUser(userEntity);
        return userDetailsEntity;
    }

    public UserDetailsDTO entityToDTOMapper(UserDetailsEntity userDetailsEntity){
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setDateOfBirth(userDetailsEntity.getDateOfBirth());
        userDetailsDTO.setEmail(userDetailsEntity.getEmail());
        userDetailsDTO.setId(userDetailsEntity.getId());
        userDetailsDTO.setHobby(userDetailsEntity.getHobby());
        userDetailsDTO.setMaritalStatus(userDetailsEntity.getMaritalStatus());
        userDetailsDTO.setTechnologies(userDetailsEntity.getTechnologies());
        UserDTO userDTO = new UserDTO();
        UserEntity userEntity = userDetailsEntity.getUser();
        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setNic(userEntity.getNic());
        userDTO.setAccountCreated(userEntity.getAccountCreated());
        userDetailsDTO.setUser(userDTO);
        return userDetailsDTO;
    }


    List<UserDetailsDTO> listEntityToListDTOMapper(List<UserDetailsEntity> userDetails){
        List<UserDetailsDTO> userDetailsDTOList = new ArrayList<>();
        for(UserDetailsEntity userDetail:userDetails){

            userDetailsDTOList.add(entityToDTOMapper(userDetail));
        }
        return userDetailsDTOList;
    }
}
