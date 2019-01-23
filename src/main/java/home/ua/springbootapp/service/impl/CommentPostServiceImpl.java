package home.ua.springbootapp.service.impl;

import home.ua.springbootapp.domain.CommentDTO;
import home.ua.springbootapp.domain.CommentPostDTO;
import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.domain.UserDTO;
import home.ua.springbootapp.entity.CommentEntity;
import home.ua.springbootapp.entity.CommentPostEntity;
import home.ua.springbootapp.entity.PostEntity;
import home.ua.springbootapp.entity.UserEntity;
import home.ua.springbootapp.repository.CommentPostRepository;
import home.ua.springbootapp.service.CommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentPostServiceImpl implements CommentPostService {
@Autowired
private CommentPostRepository commentPostRepository;
    @Override
    public void saveCommentPost(CommentPostDTO commentPost) {
        commentPostRepository.save(dtoToEntityMapper(commentPost));
    }

    @Override
    public List<CommentPostDTO> findAllCommentPosts() {

        return listEntityToListDTOMapper(commentPostRepository.findAll());
    }

    @Override
    public CommentPostDTO findCommentPostById(Long id) {

        boolean exists = commentPostRepository.existsById(id);
        if(!exists)return null;
        CommentPostDTO commentPost =entityToDTOMapper(commentPostRepository.findById(id).get());
        return commentPost;

    }

    @Override
    public CommentPostDTO updateCommentPost(Long id, CommentPostDTO commentPostToUpdate) {

        boolean exists = commentPostRepository.existsById(id);
        if(!exists)return null;
        CommentPostDTO commentFromDB = entityToDTOMapper(commentPostRepository.findById(id).get());
        commentFromDB.setId(commentPostToUpdate.getId());
        commentFromDB.setPost(commentPostToUpdate.getPost());
        commentFromDB.setComment(commentPostToUpdate.getComment());

        commentPostRepository.save(dtoToEntityMapper(commentFromDB));
        return commentFromDB;
    }

    public CommentPostEntity dtoToEntityMapper(CommentPostDTO commentPostDTO){

        CommentEntity comment = new CommentEntity();
        PostEntity post = new PostEntity();
        CommentDTO commentDTO = commentPostDTO.getComment();
        PostDTO postDTO = commentPostDTO.getPost();
        comment.setId(commentDTO.getId());
        comment.setBody(commentDTO.getBody());
        post.setId(postDTO.getId());
        post.setPostCreated(postDTO.getPostCreated());
        post.setDescription(postDTO.getDescription());
        post.setTitle(postDTO.getTitle());
        UserEntity user = new UserEntity();
        UserDTO userDTO = postDTO.getUser();
        user.setFirstName(userDTO.getFirstName());
        user.setId(userDTO.getId());
        user.setLastName(userDTO.getLastName());
        user.setNic(userDTO.getNic());
        user.setAccountCreated(userDTO.getAccountCreated());
        post.setUser(user);

        CommentPostEntity commentPostEntity = new CommentPostEntity();
        commentPostEntity.setId(commentPostDTO.getId());
        commentPostEntity.setPost(post);
        commentPostEntity.setComment(comment);
        return commentPostEntity;
    }

    public CommentPostDTO entityToDTOMapper(CommentPostEntity commentPostEntity){
        CommentPostDTO commentPostDTO = new CommentPostDTO();
        CommentEntity comment = commentPostEntity.getComment();
        PostEntity post = commentPostEntity.getPost();

        CommentDTO commentDTO = new CommentDTO();
        PostDTO postDTO = new PostDTO();

        commentDTO.setId(comment.getId());
        postDTO.setId(post.getId());
        commentDTO.setBody(comment.getBody());
        postDTO.setId(postDTO.getId());
        postDTO.setPostCreated(post.getPostCreated());
        postDTO.setDescription(post.getDescription());
        postDTO.setTitle(post.getTitle());
        UserEntity user = post.getUser();
        UserDTO userDTO  = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setId(user.getId());
        userDTO.setLastName(user.getLastName());
        userDTO.setNic(user.getNic());
        userDTO.setAccountCreated(user.getAccountCreated());
        postDTO.setUser(userDTO);

        commentPostDTO.setId(commentPostEntity.getId());
        commentPostDTO.setPost(postDTO);
        commentPostDTO.setComment(commentDTO);
        return commentPostDTO;
    }


    List<CommentPostDTO> listEntityToListDTOMapper(List<CommentPostEntity> comments){
        List<CommentPostDTO> commentPostDTOList = new ArrayList<>();
        for(CommentPostEntity comment:comments){

            commentPostDTOList.add(entityToDTOMapper(comment));
        }
        return commentPostDTOList;
    }

}
