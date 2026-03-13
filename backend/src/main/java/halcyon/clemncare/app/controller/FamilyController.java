package halcyon.clemncare.app.controller;

import halcyon.clemncare.app.dto.FamilyDTO;
import halcyon.clemncare.app.mapper.FamilyMapper;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/families")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private FamilyMapper familyMapper;

    @GetMapping("/")
    public ResponseEntity<Object> getAllFamilies() {
        List<FamilyDTO> dtos = familyService.getAllFamilies()
                .stream()
                .map(familyMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseHandler.responseBuilder("All Families", HttpStatus.OK, dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFamily(@PathVariable Long id) {
        Family family = familyService.getFamily(id);
        return ResponseHandler.responseBuilder("Requested Family", HttpStatus.OK, familyMapper.toDTO(family));
    }

    @PostMapping
    public ResponseEntity<Object> createFamily(@RequestBody FamilyDTO dto) {
        Family family = familyMapper.toEntity(dto);
        Family saved = familyService.createFamily(family);
        return ResponseHandler.responseBuilder("Family Created", HttpStatus.CREATED, familyMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFamily(@PathVariable Long id, @RequestBody FamilyDTO dto) {
        Family family = familyMapper.toEntity(dto);
        Family updated = familyService.updateFamily(id, family);
        return ResponseHandler.responseBuilder("Family Updated", HttpStatus.OK, familyMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFamily(@PathVariable Long id) {
        familyService.deleteFamily(id);
        return ResponseHandler.responseBuilder("Family Deleted", HttpStatus.OK, null);
    }
}