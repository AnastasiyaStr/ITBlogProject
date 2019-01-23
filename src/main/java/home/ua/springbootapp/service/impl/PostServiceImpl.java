package home.ua.springbootapp.service.impl;

import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.domain.UserDTO;
import home.ua.springbootapp.entity.PostEntity;
import home.ua.springbootapp.entity.UserEntity;
import home.ua.springbootapp.repository.PostRepository;
import home.ua.springbootapp.repository.UserRepository;
import home.ua.springbootapp.service.PostService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public void savePost(PostDTO post) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(post.getTitle());
        postEntity.setDescription(post.getDescription());
        postEntity.setPostCreated(post.getPostCreated());

        postRepository.save(dtoToEntityMapper(post));
    }

    @Override
    public List<PostDTO> findAllPosts() {

        return listEntityToListDTOMapper(postRepository.findAll());
    }

    @Override
    public PostDTO findPostById(Long id) {
        boolean exists = postRepository.existsById(id);
        if(!exists)return null;
        PostDTO post =entityToDTOManager(postRepository.findById(id).get());
        return post;
    }
@Override
   public PostDTO updatePostWithCheck(Long id, PostDTO postToUpdate,String name)
{
    boolean existsUser = userRepository.existsByFirstName(name);
    if(!existsUser)return null;
    UserEntity userr = userRepository.findByFirstName(name);
    boolean exists = postRepository.existsById(id);
    if(!exists)return null;
    PostEntity postFromDB = postRepository.findById(id).get();
    if(!userr.equals(postFromDB.getUser()))return null;
    postFromDB.setTitle(postToUpdate.getTitle());
    postFromDB.setDescription(postToUpdate.getDescription());
    postFromDB.setPostCreated(postToUpdate.getPostCreated());

    UserEntity user = new UserEntity();
    UserDTO userDTO = postToUpdate.getUser();
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setNic(userDTO.getNic());
    user.setId(userDTO.getId());
    user.setAccountCreated(userDTO.getAccountCreated());
    postFromDB.setUser(user);
    postRepository.save(postFromDB);
    return entityToDTOManager(postFromDB);
}


    @Override
    public PostDTO updatePost(Long id, PostDTO postToUpdate) {
        boolean exists = postRepository.existsById(id);
        if(!exists)return null;
        PostEntity postFromDB = postRepository.findById(id).get();

        postFromDB.setTitle(postToUpdate.getTitle());
        postFromDB.setDescription(postToUpdate.getDescription());
        postFromDB.setPostCreated(postToUpdate.getPostCreated());

        UserEntity user = new UserEntity();
        UserDTO userDTO = postToUpdate.getUser();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setNic(userDTO.getNic());
        user.setId(userDTO.getId());
        user.setAccountCreated(userDTO.getAccountCreated());
        postFromDB.setUser(user);
        postRepository.save(postFromDB);
        return entityToDTOManager(postFromDB);
    }

    public PostEntity dtoToEntityMapper(PostDTO post){

        PostEntity postEntity = new PostEntity();
        postEntity.setId(post.getId());
        postEntity.setTitle(post.getTitle());
        postEntity.setDescription(post.getDescription());
        postEntity.setPostCreated(post.getPostCreated());

        UserEntity userEntity = new UserEntity();
        UserDTO userDTO = post.getUser();
        userEntity.setId(userDTO.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setNic(userDTO.getNic());
        userEntity.setAccountCreated(userDTO.getAccountCreated());

        postEntity.setUser(userEntity);
        return postEntity;
    }

    public PostDTO entityToDTOManager(PostEntity postEntity){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(postEntity.getId());
        postDTO.setTitle(postEntity.getTitle());
        postDTO.setDescription(postEntity.getDescription());
        postDTO.setPostCreated(postEntity.getPostCreated());
        UserDTO userDTO = new UserDTO();
        UserEntity user = postEntity.getUser();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAccountCreated(user.getAccountCreated());
        userDTO.setId(user.getId());
        userDTO.setNic(user.getNic());
        postDTO.setUser(userDTO);
        return postDTO;
    }


    List<PostDTO> listEntityToListDTOMapper(List<PostEntity> posts){
        List<PostDTO> postDTOList = new ArrayList<>();
        for(PostEntity post:posts){

            postDTOList.add(entityToDTOManager(post));
        }
            return postDTOList;
    }
}
