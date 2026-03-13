package halcyon.clemncare.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import halcyon.clemncare.app.dto.ReportCardDTO;
import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.ReportCardService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/reportcards")
public class ReportCardController {

    @Autowired
    private ReportCardService reportCardService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getReportCard(@PathVariable Long id) {
        return ResponseHandler.responseBuilder("Report Card Retrieved Successfully", HttpStatus.OK,
                reportCardService.getReportCard(id));
    }

    @PostMapping
    public ResponseEntity<Object> createReportCard(@RequestBody ReportCardDTO reportCardDTO) {
        try{
            ReportCard createdReportCard = reportCardService.createReportCard(reportCardDTO);
            return ResponseHandler.responseBuilder("Report Card Created Successfully", HttpStatus.CREATED,
                    createdReportCard);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Error creating report card", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReportCard(@RequestBody Long id, ReportCardDTO reportCardDTO) {
        try {
            ReportCard updatedReportCard = reportCardService.updateReportCard(id, reportCardDTO);
            if(updatedReportCard != null) {
                return ResponseHandler.responseBuilder("Report Card Updated Successfully", HttpStatus.OK,
                        updatedReportCard);
            } else {
                return ResponseHandler.responseBuilder("Report Card Not Found", HttpStatus.NOT_FOUND, null);
            }
        }   catch (Exception e) {
            return ResponseHandler.responseBuilder("Error updating report card", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchReportCard(@RequestBody Long id, ReportCardDTO reportCardDTO) {
        try {
            ReportCard patchedReportCard = reportCardService.partialUpdateReportCard(id, reportCardDTO);
            if(patchedReportCard != null) {
                return ResponseHandler.responseBuilder("Report Card Patched Successfully", HttpStatus.OK,
                        patchedReportCard);
            } else {
                return ResponseHandler.responseBuilder("Report Card Not Found", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Error patching report card", HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReportCard(@PathVariable Long id) {
        if(reportCardService.getReportCard(id) != null) {
            reportCardService.deleteReportCard(id);
            return ResponseHandler.responseBuilder("Report Card Deleted Successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandler.responseBuilder("Report Card Not Found", HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/find/ids/{id}")
    public ResponseEntity<Object> findAllReportCardsByChildId(@PathVariable Long id, Pageable pageable) {
        try {
        Page<ReportCard> reportCards = reportCardService.getReportCardsByChildId(id, pageable);
        
        if (reportCards.isEmpty()) {
            return ResponseHandler.responseBuilder("No Report Cards found for Child ID: " + id, HttpStatus.NOT_FOUND, null);
        }

        return ResponseHandler.responseBuilder("Report Cards for Child ID: " + id, HttpStatus.OK, reportCards);
    } catch (EntityNotFoundException e) {
        return ResponseHandler.responseBuilder("Child with ID " + id + " not found", HttpStatus.NOT_FOUND, null);
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions as needed
        return ResponseHandler.responseBuilder("Error retrieving report cards", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
    }

}
