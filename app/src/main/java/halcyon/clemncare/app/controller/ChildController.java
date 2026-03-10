package halcyon.clemncare.app.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.dto.ChildDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.ChildService;

@RestController
@RequestMapping("/api/children")
public class ChildController {

    private static final Logger logger = LoggerFactory.getLogger(ChildController.class);

    @Autowired
    private ChildService childService;

    @GetMapping("/")
    public ResponseEntity<Object> getChildren() {
        return ResponseHandler.responseBuilder("Requested All Child Data", HttpStatus.OK,
                childService.getAllChildren());
    }

    @GetMapping("/find/ages/{age}")
    public ResponseEntity<Object> getChildrenByAge(@PathVariable int age) {
        List<Child> children = childService.findChildrenByAge(age);

        if (children != null) {
            return ResponseHandler.responseBuilder("Children found for the specified age", HttpStatus.OK,
                    children);
        } else {
            return ResponseHandler.responseBuilder("No children found for the specified age", HttpStatus.NOT_FOUND,
                    null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getChild(@PathVariable Long id) {
        Optional<Child> childOptional = Optional.ofNullable(childService.getChild(id));

        if (childOptional.isPresent()) {
            return ResponseHandler.responseBuilder("Requested Specific Child Data", HttpStatus.OK, childOptional.get());
        } else {
            return ResponseHandler.responseBuilder("Child not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createChild(@RequestBody ChildDTO childDTO) {
        try {
            Child createdChild = childService.createChild(childDTO);
            return ResponseHandler.responseBuilder("Child Created Successfully", HttpStatus.CREATED, createdChild);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Child Creation Failed", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping("/{id}")
public ResponseEntity<Object> updateChild(@PathVariable Long id, @RequestBody ChildDTO childDTO) {
    try {
        logger.info("Updating child with ID: {}", id);
        
        Child updatedChild = childService.updateChild(id, childDTO);
        
        if (updatedChild != null) {
            logger.info("Child Updated Successfully: {}", updatedChild);
            return ResponseHandler.responseBuilder("Child Updated Successfully", HttpStatus.OK, updatedChild);
        } else {
            logger.warn("Child with ID {} not found", id);
            return ResponseHandler.responseBuilder("Child with ID " + id + " not found", HttpStatus.NOT_FOUND, null);
        }
    } catch (Exception e) {
        logger.error("Child Update Failed", e);
        return ResponseHandler.responseBuilder("Child Update Failed", HttpStatus.BAD_REQUEST, null);
    }
}


    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdateChild( @PathVariable Long id, @RequestBody ChildDTO childDTO) {
        try {
            Child updatedChild = childService.partialUpdateChild(id, childDTO);
            if(updatedChild != null) {
                return ResponseHandler.responseBuilder("Child Updated Successfully", HttpStatus.OK, updatedChild);
            } else {
                return ResponseHandler.responseBuilder("Child with ID " + id + " not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Child Update Failed", HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteChild(@PathVariable Long id) {
        if(childService.getChild(id) != null) {
            childService.deleteChild(id);
            return ResponseHandler.responseBuilder("Child Deleted Successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandler.responseBuilder("Child with ID " + id + " not found. Could not delete.", HttpStatus.NOT_FOUND, null);
        }
    }

}