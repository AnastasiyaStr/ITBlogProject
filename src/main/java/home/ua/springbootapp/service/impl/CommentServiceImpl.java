package home.ua.springbootapp.service.impl;

import home.ua.springbootapp.domain.CommentDTO;
import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.domain.TagDTO;
import home.ua.springbootapp.entity.*;
import home.ua.springbootapp.repository.CommentRepository;
import home.ua.springbootapp.repository.TagRepository;
import home.ua.springbootapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public void saveComment(CommentDTO comment) {
        commentRepository.save(dtoToEntityMapper(comment));
    }

    @Override
    public List<CommentDTO> findAllComments() {

        return listEntityToListDTOMapper(commentRepository.findAll());
    }

    @Override
    public CommentDTO findCommentById(Long id) {

        boolean exists = commentRepository.existsById(id);
        if(!exists)return null;
       CommentDTO comment =entityToDTOMapper(commentRepository.findById(id).get());
        return comment;


    }

    @Override
    public CommentDTO updateComment(Long id, CommentDTO commentToUpdate) {
        boolean exists = commentRepository.existsById(id);
        if(!exists)return null;
        CommentDTO commentFromDB = entityToDTOMapper(commentRepository.findById(id).get());
        commentFromDB.setId(commentToUpdate.getId());
        commentFromDB.setBody(commentToUpdate.getBody());

        commentRepository.save(dtoToEntityMapper(commentFromDB));
        return commentFromDB;
    }


    public CommentEntity dtoToEntityMapper(CommentDTO commentDTO){
        CommentEntity commentEntity = new CommentEntity();


        commentEntity.setBody(commentDTO.getBody());
        commentEntity.setId(commentDTO.getId());


        return commentEntity;
    }

    public CommentDTO entityToDTOMapper(CommentEntity commentEntity){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setBody(commentEntity.getBody());

        return commentDTO;
    }


    List<CommentDTO> listEntityToListDTOMapper(List<CommentEntity> comments){
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(CommentEntity comment:comments){

            commentDTOList.add(entityToDTOMapper(comment));
        }
        return commentDTOList;
    }

}
