package home.ua.springbootapp.controller;

import home.ua.springbootapp.domain.CommentDTO;
import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.service.CommentService;
import home.ua.springbootapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(
            @RequestBody CommentDTO commentDTO
    ){
        //System.out.println(commentDTO.getTitle()+" "+  postDTO.getDescription());
        commentService.saveComment(commentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //Витягнути з бази
    @GetMapping
    public ResponseEntity<?> getComments(){
        List<CommentDTO> comments = commentService.findAllComments();
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

    @GetMapping("{commentId}")
    public ResponseEntity<?>getCommentById(@PathVariable("commentId") Long id)
    {
        CommentDTO comment = commentService.findCommentById(id);
        if(comment == null) return new ResponseEntity<>("No element found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @PutMapping("{comment1Id}")
    public ResponseEntity<?> updateComment(
            @PathVariable("comment1Id") Long id,
            @RequestBody CommentDTO commentToUpdate
    )
    {
       CommentDTO comment = commentService.updateComment(id,commentToUpdate);

        if(comment == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }



}
