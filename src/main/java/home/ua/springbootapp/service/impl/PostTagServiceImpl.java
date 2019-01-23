package home.ua.springbootapp.service.impl;

import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.domain.PostTagDTO;
import home.ua.springbootapp.domain.TagDTO;
import home.ua.springbootapp.domain.UserDTO;
import home.ua.springbootapp.entity.PostEntity;
import home.ua.springbootapp.entity.PostTagEntity;
import home.ua.springbootapp.entity.TagEntity;
import home.ua.springbootapp.entity.UserEntity;
import home.ua.springbootapp.repository.PostTagRepository;
import home.ua.springbootapp.repository.TagRepository;
import home.ua.springbootapp.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostTagServiceImpl implements PostTagService {
    @Autowired
    private PostTagRepository postTagRepository;
    @Override
    public void savePostTag(PostTagDTO postTag) {
        postTagRepository.save(dtoToEntityMapper(postTag));
    }

    @Override
    public List<PostTagDTO> findAllPostTags() {
        return  listEntityToListDTOMapper(postTagRepository.findAll());
    }

    @Override
    public PostTagDTO findPostTagById(Long id) {
        boolean exists = postTagRepository.existsById(id);
        if(!exists)return null;
        PostTagDTO tag =entityToDTOMapper(postTagRepository.findById(id).get());
        return tag;
    }

    @Override
    public PostTagDTO updatePostTag(Long id, PostTagDTO postTagToUpdate) {
        boolean exists = postTagRepository.existsById(id);
        if(!exists)return null;
        PostTagEntity tagFromDB = postTagRepository.findById(id).get();
      PostTagDTO postTag = new PostTagDTO();
        postTag.setTag(postTagToUpdate.getTag());

        postTag.setPost(postTagToUpdate.getPost());
        postTagRepository.save(dtoToEntityMapper(postTag));
        return entityToDTOMapper(tagFromDB);

    }
    public PostTagEntity dtoToEntityMapper(PostTagDTO postTagDTO){
        PostTagEntity postTagEntity = new PostTagEntity();
        PostDTO postDTO = postTagDTO.getPost();
       PostEntity postEntity= new PostEntity();
        UserEntity userEntity = new UserEntity();
        UserDTO userDTO = postDTO.getUser();
        userEntity.setId(userDTO.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setNic(userDTO.getNic());
        userEntity.setAccountCreated(userDTO.getAccountCreated());
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setPostCreated(postDTO.getPostCreated());
       postEntity.setId(postDTO.getId());
        postEntity.setUser(userEntity);
        postEntity.setDescription(postDTO.getDescription());
        TagDTO tagDTO = postTagDTO.getTag();
        TagEntity tagEntity = new TagEntity();
        tagEntity.setId(tagDTO.getId());
        tagEntity.setTagName(tagDTO.getTagName());

        postTagEntity.setPost(postEntity);
        postTagEntity.setTag(tagEntity);
        return  postTagEntity;
    }

    public PostTagDTO entityToDTOMapper(PostTagEntity postTagEntity){
        PostTagDTO postTagDTO = new PostTagDTO();
        PostEntity postEntity = postTagEntity.getPost();
        PostDTO postDTO= new PostDTO();
        UserDTO userDTO = new UserDTO();
        UserEntity userEntity = postEntity.getUser();

        userDTO.setId(userEntity.getId());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setNic(userEntity.getNic());
        userDTO.setAccountCreated(userEntity.getAccountCreated());
        postDTO.setUser(userDTO);

        postDTO.setTitle(postEntity.getTitle());
        postDTO.setPostCreated(postEntity.getPostCreated());
        postDTO.setId(postEntity.getId());

        postDTO.setDescription(postEntity.getDescription());

        TagEntity tagEntity = postTagEntity.getTag();
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tagEntity.getId());
        tagDTO.setTagName(tagEntity.getTagName());

        postTagDTO.setId(postTagEntity.getId());
        postTagDTO.setPost(postDTO);
        postTagDTO.setTag(tagDTO);
        return  postTagDTO;
    }


    List<PostTagDTO> listEntityToListDTOMapper(List<PostTagEntity> tags){
        List<PostTagDTO> tagDTOList = new ArrayList<>();
        for(PostTagEntity tag:tags){

            tagDTOList.add(entityToDTOMapper(tag));
        }
        return tagDTOList;
    }

}
