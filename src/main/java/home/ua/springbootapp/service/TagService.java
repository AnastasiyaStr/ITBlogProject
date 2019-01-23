package home.ua.springbootapp.service;

import home.ua.springbootapp.domain.TagDTO;
import home.ua.springbootapp.entity.TagEntity;
import home.ua.springbootapp.entity.UserEntity;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface TagService {

    void saveTag(TagDTO tag);
    List<TagDTO> findAllTags();
    TagDTO findTagById(Long id);
    TagDTO updateTag(Long id, TagDTO TagToUpdate);
}
