package home.ua.springbootapp.service.impl;

        import home.ua.springbootapp.domain.TagDTO;
        import home.ua.springbootapp.domain.UserDTO;
        import home.ua.springbootapp.entity.TagEntity;
        import home.ua.springbootapp.entity.UserEntity;
        import home.ua.springbootapp.exceptions.AlreadyExistsException;
        import home.ua.springbootapp.exceptions.NotFoundException;
        import home.ua.springbootapp.repository.UserRepository;
        import home.ua.springbootapp.service.UserService;
        import home.ua.springbootapp.utils.ObjectMapperUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;
        import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;
    @Override
    public void saveUser(UserDTO user) {
        boolean exists = userRepository.existsByFirstName(user.getFirstName());
        if(exists) throw new AlreadyExistsException("This element already exists!!!");

        userRepository.save(modelMapper.map(user,UserEntity.class));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return modelMapper.mapAll(userRepository.findAll(),UserDTO.class);
    }

    @Override
    public UserDTO findUserById(Long id) {
        boolean exists = userRepository.existsById(id);
        //if(!exists) throw new NotFoundException("Element Not Found");
        UserEntity user = userRepository.findById(id).orElseThrow(()->new NotFoundException("User Not Found"));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userToUpdate) {
        boolean exists = userRepository.existsById(id);
        if(!exists)return null;
        UserEntity userFromDB = userRepository.findById(id).get();
        userFromDB.setFirstName(userToUpdate.getFirstName());
        userFromDB.setLastName(userToUpdate.getLastName());
        userFromDB.setNic(userToUpdate.getNic());

        userFromDB.setAccountCreated(userToUpdate.getAccountCreated());
        userRepository.save(userFromDB);
        return modelMapper.map(userFromDB,UserDTO.class);
    }
    public UserEntity dtoToEntityMapper(UserDTO userDTO){
       UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setAccountCreated(userDTO.getAccountCreated());
        userEntity.setNic(userDTO.getNic());
        userEntity.setId(userDTO.getId());
        return userEntity;
    }

    public UserDTO entityToDTOMapper(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setAccountCreated(userEntity.getAccountCreated());
        userDTO.setNic(userEntity.getNic());
        userDTO.setId(userEntity.getId());


        return  userDTO;
    }


    List<UserDTO> listEntityToListDTOMapper(List<UserEntity> users){
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserEntity user:users){

           userDTOList.add(entityToDTOMapper(user));
        }
        return userDTOList;
    }





}
