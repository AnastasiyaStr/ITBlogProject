package home.ua.springbootapp.service;

import home.ua.springbootapp.domain.CommentDTO;
import home.ua.springbootapp.domain.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {
    void saveComment(CommentDTO comment);
    List<CommentDTO> findAllComments();
    CommentDTO findCommentById(Long id);
    CommentDTO updateComment(Long id, CommentDTO CommentToUpdate);
}
