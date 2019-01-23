package home.ua.springbootapp.service;

import home.ua.springbootapp.domain.PostTagDTO;
import home.ua.springbootapp.domain.TagDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostTagService {
    void savePostTag(PostTagDTO postTag);
    List<PostTagDTO> findAllPostTags();
    PostTagDTO findPostTagById(Long id);
    PostTagDTO updatePostTag(Long id, PostTagDTO postTagToUpdate);
}
