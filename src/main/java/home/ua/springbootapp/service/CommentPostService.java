package home.ua.springbootapp.service;

import home.ua.springbootapp.domain.CommentDTO;
import home.ua.springbootapp.domain.CommentPostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentPostService {
    void saveCommentPost(CommentPostDTO commentPost);
    List<CommentPostDTO> findAllCommentPosts();
    CommentPostDTO findCommentPostById(Long id);
    CommentPostDTO updateCommentPost(Long id, CommentPostDTO CommentPostToUpdate);
}
