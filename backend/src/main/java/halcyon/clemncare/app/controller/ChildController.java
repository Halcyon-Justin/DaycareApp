package halcyon.clemncare.app.controller;

import halcyon.clemncare.app.dto.ChildDTO;
import halcyon.clemncare.app.mapper.ChildMapper;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/children")
public class ChildController {

    @Autowired
    private ChildService childService;

    @Autowired
    private ChildMapper childMapper;

    @GetMapping("/")
    public ResponseEntity<Object> getAllChildren(Pageable pageable) {
        List<ChildDTO> dtos = childService.getAllChildren(pageable)
                .stream()
                .map(childMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseHandler.responseBuilder("All Children", HttpStatus.OK, dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getChild(@PathVariable Long id) {
        Child child = childService.getChild(id);
        return ResponseHandler.responseBuilder("Requested Child", HttpStatus.OK, childMapper.toDTO(child));
    }

    @GetMapping("/find/ages/{age}")
    public ResponseEntity<Object> getChildrenByAge(@PathVariable int age) {
        List<ChildDTO> dtos = childService.findChildrenByAge(age)
                .stream()
                .map(childMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseHandler.responseBuilder("Children found for the specified age", HttpStatus.OK, dtos);
    }

    @GetMapping("/active")
    public ResponseEntity<Object> getActiveChildren() {
        List<ChildDTO> dtos = childService.getEnrolledChildren()
                .stream()
                .map(childMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseHandler.responseBuilder("Active Children", HttpStatus.OK, dtos);
    }

    @PostMapping
    public ResponseEntity<Object> createChild(@RequestBody ChildDTO dto) {
        Child child = childMapper.toEntity(dto);
        Child saved = childService.createChild(child);
        return ResponseHandler.responseBuilder("Child Created", HttpStatus.CREATED, childMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateChild(@PathVariable Long id, @RequestBody ChildDTO dto) {
        Child child = childMapper.toEntity(dto);
        Child updated = childService.updateChild(id, child);
        return ResponseHandler.responseBuilder("Child Updated", HttpStatus.OK, childMapper.toDTO(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdateChild(@PathVariable Long id, @RequestBody ChildDTO dto) {
        Child child = childMapper.toEntity(dto);
        Child updated = childService.partialUpdateChild(id, child);
        return ResponseHandler.responseBuilder("Child Updated", HttpStatus.OK, childMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteChild(@PathVariable Long id) {
        childService.deleteChild(id);
        return ResponseHandler.responseBuilder("Child Deleted", HttpStatus.OK, null);
    }
}