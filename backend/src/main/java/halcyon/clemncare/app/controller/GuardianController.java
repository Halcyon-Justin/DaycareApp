package halcyon.clemncare.app.controller;

import halcyon.clemncare.app.dto.GuardianDTO;
import halcyon.clemncare.app.mapper.GuardianMapper;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guardians")
public class GuardianController {

    @Autowired
    private GuardianService guardianService;

    @Autowired
    private GuardianMapper guardianMapper;

    @GetMapping("/")
    public ResponseEntity<Object> getAllGuardians() {
        List<GuardianDTO> dtos = guardianService.getAllGuardians()
                .stream().map(guardianMapper::toDTO).collect(Collectors.toList());
        return ResponseHandler.responseBuilder("All Guardians", HttpStatus.OK, dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGuardian(@PathVariable Long id) {
        Guardian guardian = guardianService.getGuardian(id);
        return ResponseHandler.responseBuilder("Requested Guardian", HttpStatus.OK, guardianMapper.toDTO(guardian));
    }

    @GetMapping("/family/{familyId}")
    public ResponseEntity<Object> getGuardiansByFamily(@PathVariable Long familyId) {
        List<GuardianDTO> dtos = guardianService.getGuardiansByFamilyId(familyId)
                .stream().map(guardianMapper::toDTO).collect(Collectors.toList());
        return ResponseHandler.responseBuilder("Guardians for Family", HttpStatus.OK, dtos);
    }

    @PostMapping
    public ResponseEntity<Object> createGuardian(@RequestBody GuardianDTO dto) {
        Guardian guardian = guardianMapper.toEntity(dto);
        Guardian saved = guardianService.createGuardian(guardian);
        return ResponseHandler.responseBuilder("Guardian Created", HttpStatus.CREATED, guardianMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGuardian(@PathVariable Long id, @RequestBody GuardianDTO dto) {
        Guardian guardian = guardianMapper.toEntity(dto);
        Guardian updated = guardianService.updateGuardian(id, guardian);
        return ResponseHandler.responseBuilder("Guardian Updated", HttpStatus.OK, guardianMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGuardian(@PathVariable Long id) {
        guardianService.deleteGuardian(id);
        return ResponseHandler.responseBuilder("Guardian Deleted", HttpStatus.OK, null);
    }
}