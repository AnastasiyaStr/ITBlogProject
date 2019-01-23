package home.ua.springbootapp.controller;

import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.entity.PostEntity;
import home.ua.springbootapp.entity.UserEntity;
import home.ua.springbootapp.service.PostService;
import home.ua.springbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestBody PostDTO postDTO
    ){
        System.out.println(postDTO.getTitle()+" "+  postDTO.getDescription());
        postService.savePost(postDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //Витягнути з бази
    @GetMapping
    public ResponseEntity<?> getPosts(){
        List<PostDTO> posts = postService.findAllPosts();
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("{postId}")
    public ResponseEntity<?>getPostById(@PathVariable("postId") Long id)
    {
        PostDTO post = postService.findPostById(id);
        if(post == null) return new ResponseEntity<>("No element found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PutMapping("{post1Id:\\d\\D{1,50}}")/*[a-z]{1,50}*/
    public ResponseEntity<?> updatePost(
            @PathVariable("post1Id")String word,
            @RequestBody PostDTO postToUpdate
    )
    {

        Long id = Long.parseLong(word.substring(0,1));
        String name = word.substring(1);
        PostDTO post = postService.updatePostWithCheck(id,postToUpdate,name);

        if(post == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(post,HttpStatus.OK);
    }



}
