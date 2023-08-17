package com.example.tutorialv2.controller;

import com.example.tutorialv2.data.vo.v1.TutorialVO;
import com.example.tutorialv2.data.vo.v2.TutorialVOV2;
import com.example.tutorialv2.service.TutorialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@Tag(name = "Tutorial", description = "Tutorial Controller")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;


    @GetMapping("/v1/tutorials")
    public ResponseEntity<List<TutorialVO>> getAllTutorials(@RequestParam(required = false) String title) throws Exception {
        return ResponseEntity.ok(tutorialService.findAll(title));


    }

    @GetMapping("/v1/tutorials/search")
    public ResponseEntity<List<TutorialVO>> buscarTutorials(@RequestParam("buscar") String buscar) throws Exception{
        return ResponseEntity.ok(tutorialService.searchTutorials(buscar));

    }

    @GetMapping("/v1/tutorials/{id}") //GET BY ID
    public ResponseEntity<TutorialVO> getTutorialById(@PathVariable("id") long id) throws Exception{
        return ResponseEntity.ok(tutorialService.findById(id));
    }

    @PostMapping("/v1/tutorials")
    public ResponseEntity<TutorialVO> createTutorial(@RequestBody TutorialVO tutorial)throws Exception {
        return ResponseEntity.ok(tutorialService.createTutorial(tutorial));
    }

    @PostMapping("/v2/tutorials")
    public ResponseEntity<TutorialVOV2> createTutorialV2(@RequestBody TutorialVOV2 tutorial)throws Exception {
        return ResponseEntity.ok(tutorialService.createTutorialV2(tutorial));
    }

    @PutMapping("/v1/tutorials/{id}")
    public ResponseEntity<TutorialVO> updateTutorial(@PathVariable("id") long id, @RequestBody TutorialVO tutorial) throws Exception{
        return ResponseEntity.ok(tutorialService.updateTutorial(tutorial));
    }

    @DeleteMapping("/v1/tutorials/{id}")
    public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) throws Exception{

        tutorialService.deleteTutorial(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/v1/tutorials/published")
    public ResponseEntity<List<TutorialVO>> findByPublished() throws Exception{
        return ResponseEntity.ok(tutorialService.findByPublished());
    }


}
