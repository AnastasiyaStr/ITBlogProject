package home.ua.springbootapp.controller;

import home.ua.springbootapp.domain.CommentDTO;
import home.ua.springbootapp.domain.CommentPostDTO;
import home.ua.springbootapp.service.CommentPostService;
import home.ua.springbootapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/commentPosts")
public class CommentPostController {
    @Autowired
    private CommentPostService commentPostService;

    @PostMapping
    public ResponseEntity<?> createCommentPost(
            @RequestBody CommentPostDTO commentPostDTO
    ){
        //System.out.println(commentDTO.getTitle()+" "+  postDTO.getDescription());
        commentPostService.saveCommentPost(commentPostDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //Витягнути з бази
    @GetMapping
    public ResponseEntity<?> getComments(){
        List<CommentPostDTO> commentPosts = commentPostService.findAllCommentPosts();
        return new ResponseEntity<>(commentPosts,HttpStatus.OK);
    }

    @GetMapping("{commentId}")
    public ResponseEntity<?>getCommentById(@PathVariable("commentId") Long id)
    {
        CommentPostDTO commentPost = commentPostService.findCommentPostById(id);
        if(commentPost == null) return new ResponseEntity<>("No element found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(commentPost,HttpStatus.OK);
    }

    @PutMapping("{commentPost1Id}")
    public ResponseEntity<?> updateCommentPost(
            @PathVariable("commentPost1Id") Long id,
            @RequestBody CommentPostDTO commentPostToUpdate
    )
    {
        CommentPostDTO commentPost = commentPostService.updateCommentPost(id,commentPostToUpdate);

        if(commentPost == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(commentPost,HttpStatus.OK);
    }

}
