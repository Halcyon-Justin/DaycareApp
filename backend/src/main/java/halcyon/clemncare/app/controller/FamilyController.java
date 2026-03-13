package halcyon.clemncare.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.dto.FamilyDTO;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.FamilyService;

@RestController
@RequestMapping("/api/families")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<Object> getFamilies() {
        return ResponseHandler.responseBuilder("Requested All Family Data", HttpStatus.OK,
                familyService.getAllFamilies());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getFamily(@PathVariable Long id) {
        Optional<Family> familyOptional = familyService.getFamily(id);
        if(familyOptional.isPresent()) {
            return ResponseHandler.responseBuilder("Requested Specific Family Data", HttpStatus.OK, familyOptional.get());
        } else {
            return ResponseHandler.responseBuilder("Family not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createFamily(@RequestBody FamilyDTO familyDTO) {
        try {
            Family createdFamily = familyService.createFamily(familyDTO);
            return ResponseHandler.responseBuilder("Family Created Successfully", HttpStatus.CREATED,
                    createdFamily);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFamily(@PathVariable Long id) {
        if(familyService.getFamily(id) != null) {
            familyService.deleteFamily(id);
            return ResponseHandler.responseBuilder("Family Deleted Successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandler.responseBuilder("Family not found", HttpStatus.NOT_FOUND, null);
        }
    }
}
