package halcyon.clemncare.app.controller;

import java.util.Optional;

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

import halcyon.clemncare.app.dto.HomeAddressDTO;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.HomeAddressService;

@RestController
@RequestMapping("/api/homeaddresses")
public class HomeAddressController {

    @Autowired
    private HomeAddressService homeAddressService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHomeAddress(@PathVariable Long id) {
        Optional<HomeAddress> addressOptional = homeAddressService.getAddress(id);
        if (addressOptional.isPresent()) {
            return ResponseHandler.responseBuilder("Requested Specific Address Data", HttpStatus.OK,
                    addressOptional.get());
        } else {
            return ResponseHandler.responseBuilder("Address not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createAddress(@RequestBody HomeAddressDTO homeAddressDTO) {
        try {
            HomeAddress createdAddress = homeAddressService.createAddress(homeAddressDTO);
            return ResponseHandler.responseBuilder("Address Created Successfully", HttpStatus.CREATED, createdAddress);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Address Creation Failed", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAddress(@RequestBody Long id, HomeAddressDTO homeAddressDTO) {
        try {
            HomeAddress updatedAddress = homeAddressService.updateAddress(id, homeAddressDTO);
            if (updatedAddress != null) {
                return ResponseHandler.responseBuilder("Address Updated Successfully", HttpStatus.OK, updatedAddress);
            } else {
                return ResponseHandler.responseBuilder("Address Update Failed", HttpStatus.BAD_REQUEST, null);
            }
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Address Update Failed", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdateAddress(@RequestBody Long id, HomeAddressDTO homeAddressDTO) {
        try {
            HomeAddress updatedAddress = homeAddressService.partialUpdateAddress(id, homeAddressDTO);
            if (updatedAddress != null) {
                return ResponseHandler.responseBuilder("Address Updated Successfully", HttpStatus.OK, updatedAddress);
            } else {
                return ResponseHandler.responseBuilder("Address Update Failed", HttpStatus.BAD_REQUEST, null);
            }
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Address Update Failed", HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Long id) {
        if (homeAddressService.getAddress(id) != null) {
            homeAddressService.deleteAddress(id);
            return ResponseHandler.responseBuilder("Address Deleted Successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandler.responseBuilder("Address with ID " + id + " not found", HttpStatus.NOT_FOUND, null);
        }
    }

}
