package halcyon.clemncare.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.dto.GuardianDTO;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.GuardianService;

@RestController
@RequestMapping("/api/guardian")
public class GuardianController {

    @Autowired
    private GuardianService guardianService;

    @GetMapping("/")
    public ResponseEntity<Object> getGuardians() {
        return ResponseHandler.responseBuilder("Requested All Guardian Data", HttpStatus.OK,
                guardianService.getAllGuardians());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGuardian(@PathVariable Long id) {
         Optional<Guardian> guardianOptional = guardianService.getGuardian(id);
         if(guardianOptional.isPresent()) {
             return ResponseHandler.responseBuilder("Requested Specific Guardian Data", HttpStatus.OK, guardianOptional.get());
         } else {
             return ResponseHandler.responseBuilder("Guardian not found", HttpStatus.NOT_FOUND, null);
         }
    }

    @PostMapping
    public ResponseEntity<Object> createGuardian(@RequestBody GuardianDTO guardianDTO) {
        try {
            Guardian createdGuardian = guardianService.createGuardian(guardianDTO);
            return ResponseHandler.responseBuilder("Guardian Created Successfully", HttpStatus.CREATED,
                    createdGuardian);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Guardian Creation Failed", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGuardian(@RequestBody Long id, GuardianDTO guardianDTO) {
        try {
            Guardian updatedGuardian = guardianService.updateGuardian(id, guardianDTO);
            if(updatedGuardian != null) {
                return ResponseHandler.responseBuilder("Guardian Updated Successfully", HttpStatus.OK, updatedGuardian);
            } else {
                return ResponseHandler.responseBuilder("Guardian not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) { 
            return ResponseHandler.responseBuilder("Guardian Update Failed", HttpStatus.BAD_REQUEST, null);
        }
               
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGuardian(@PathVariable Long id) {
        if(guardianService.getGuardian(id) != null) {
            guardianService.deleteGuardian(id);
            return ResponseHandler.responseBuilder("Guardian Deleted Successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandler.responseBuilder("Guardian not found", HttpStatus.NOT_FOUND, null);
        }
    }

}
