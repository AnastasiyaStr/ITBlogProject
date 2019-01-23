package home.ua.springbootapp.controller;

import home.ua.springbootapp.domain.PostDTO;
import home.ua.springbootapp.domain.TagDTO;
import home.ua.springbootapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping
    public ResponseEntity<?> createTag(
            @RequestBody TagDTO tagDTO
    ){
        System.out.println(tagDTO.getTagName());
      tagService.saveTag(tagDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getTags(){
        List<TagDTO> tags = tagService.findAllTags();
        return new ResponseEntity<>(tags,HttpStatus.OK);
    }


    @GetMapping("{tagId}")
    public ResponseEntity<?>getTagById(@PathVariable("tagId") Long id)
    {
        TagDTO tag = tagService.findTagById(id);
        if(tag == null) return new ResponseEntity<>("No element found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tag,HttpStatus.OK);
    }

    @PutMapping("{tagId}")
    public ResponseEntity<?> updateTag(
            @PathVariable("tagId") Long id,
            @RequestBody TagDTO tagToUpdate
    )
    {
        TagDTO tag = tagService.updateTag(id,tagToUpdate);

        if(tag == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tag,HttpStatus.OK);
    }


}
