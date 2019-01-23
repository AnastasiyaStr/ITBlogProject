package home.ua.springbootapp.controller;

import home.ua.springbootapp.domain.PostTagDTO;
import home.ua.springbootapp.domain.TagDTO;
import home.ua.springbootapp.service.PostService;
import home.ua.springbootapp.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/postTags")
public class PostTagController  {
    @Autowired
    private PostTagService postTagService;

    @PostMapping
    public ResponseEntity<?> createPostTag(
            @RequestBody PostTagDTO postTagDTO
    ){
        postTagService.savePostTag(postTagDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getPostTags(){
        List<PostTagDTO> postTags = postTagService.findAllPostTags();
        return new ResponseEntity<>(postTags,HttpStatus.OK);
    }


    @GetMapping("{tagId}")
    public ResponseEntity<?>getPostTagById(@PathVariable("tagId") Long id)
    {
        PostTagDTO tag = postTagService.findPostTagById(id);
        if(tag == null) return new ResponseEntity<>("No element found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tag,HttpStatus.OK);
    }

    @PutMapping("{tagId}")
    public ResponseEntity<?> updatePostTag(
            @PathVariable("tagId") Long id,
            @RequestBody PostTagDTO postTagToUpdate
    )
    {
        PostTagDTO tag = postTagService.updatePostTag(id,postTagToUpdate);

        if(tag == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tag,HttpStatus.OK);
    }



}
