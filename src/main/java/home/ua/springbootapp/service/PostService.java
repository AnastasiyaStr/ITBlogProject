package home.ua.springbootapp.service;

import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.entity.PostEntity;
import home.ua.springbootapp.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostService {
    void savePost(PostDTO post);
    List<PostDTO> findAllPosts();
    PostDTO findPostById(Long id);
    PostDTO updatePost(Long id, PostDTO PostToUpdate);
    PostDTO updatePostWithCheck(Long id, PostDTO postToUpdate,String name);
}
