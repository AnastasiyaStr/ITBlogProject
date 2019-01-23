package home.ua.springbootapp.service.impl;

import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.domain.TagDTO;
import home.ua.springbootapp.domain.UserDTO;
import home.ua.springbootapp.entity.PostEntity;
import home.ua.springbootapp.entity.TagEntity;
import home.ua.springbootapp.entity.UserEntity;
import home.ua.springbootapp.repository.PostRepository;
import home.ua.springbootapp.repository.TagRepository;
import home.ua.springbootapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Override
    public void saveTag(TagDTO tag) {
        tagRepository.save(dtoToEntityMapper(tag));
    }

    @Override
    public List<TagDTO >findAllTags() {

        return  listEntityToListDTOMapper(tagRepository.findAll());
    }

    @Override
    public TagDTO findTagById(Long id) {
        boolean exists = tagRepository.existsById(id);
        if(!exists)return null;
        TagDTO tag =entityToDTOMapper(tagRepository.findById(id).get());
        return tag;
    }

    @Override
    public TagDTO updateTag(Long id, TagDTO  tagToUpdate) {
        boolean exists = tagRepository.existsById(id);
        if(!exists)return null;
        TagEntity tagFromDB = tagRepository.findById(id).get();
        tagFromDB.setTagName(tagToUpdate.getTagName());
        tagFromDB.setId( tagFromDB.getId());

        tagRepository.save(tagFromDB);
        return entityToDTOMapper(tagFromDB);

    }


    public TagEntity dtoToEntityMapper(TagDTO tagDTO){
        TagEntity tagEntity = new TagEntity();


        tagEntity.setTagName(tagDTO.getTagName());
        tagEntity.setId(tagDTO.getId());


        return tagEntity;
    }

    public TagDTO entityToDTOMapper(TagEntity tagEntity){
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tagEntity.getId());
        tagDTO.setTagName(tagEntity.getTagName());

        return tagDTO;
    }


    List<TagDTO> listEntityToListDTOMapper(List<TagEntity> tags){
        List<TagDTO> tagDTOList = new ArrayList<>();
        for(TagEntity tag:tags){

           tagDTOList.add(entityToDTOMapper(tag));
        }
        return tagDTOList;
    }

}
