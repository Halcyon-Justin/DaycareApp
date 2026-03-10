package halcyon.clemncare.app.controller;

import java.util.List;
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

import halcyon.clemncare.app.dto.InvoiceDTO;
import halcyon.clemncare.app.exception.FamilyNotFoundException;
import halcyon.clemncare.app.model.Invoice;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public ResponseEntity<Object> getInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        if(invoices.isEmpty()) {
            return ResponseHandler.responseBuilder("No Invoices Found", HttpStatus.NOT_FOUND, null);
        } else {
            return ResponseHandler.responseBuilder("Requested All Invoice Data", HttpStatus.OK, invoices);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInvoice(@PathVariable Long id) {
        Optional<Invoice> invoiceOptional = invoiceService.getInvoice(id);

        if (invoiceOptional.isPresent()) {
            return ResponseHandler.responseBuilder("Requested Specific Invoice Data", HttpStatus.OK, invoiceOptional.get());
        } else {
            return ResponseHandler.responseBuilder("Invoice not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/find/family/{familyId}")
    public ResponseEntity<Object> getInvoicesByFamilyId(@PathVariable Long familyId) {
        List<Invoice> invoices = invoiceService.findInvoicesByFamilyId(familyId);
        if(invoices.isEmpty()) {
            return ResponseHandler.responseBuilder("No Invoices Found", HttpStatus.NOT_FOUND, null);
        } else {
            return ResponseHandler.responseBuilder("Requested All Invoice Data for Family ID: " + familyId, HttpStatus.OK, invoices);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        try {
            Invoice invoice = invoiceService.createInvoice(invoiceDTO);
            return ResponseHandler.responseBuilder("Invoice Created Successfully", HttpStatus.CREATED, invoice);
        } catch (FamilyNotFoundException e) {
            return ResponseHandler.responseBuilder("Family not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Error Creating Invoice", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInvoice(@RequestBody Long id, InvoiceDTO invoiceDTO) {
        try {
            Invoice updatedInvoice = invoiceService.partialUpdateInvoice(id, invoiceDTO);
            if(updatedInvoice != null) {
                return ResponseHandler.responseBuilder("Invoice Updated Successfully", HttpStatus.OK, updatedInvoice);
            } else {
                return ResponseHandler.responseBuilder("Invoice with ID " + id + " not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Error Updating Invoice", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchInvoice(@RequestBody InvoiceDTO invoiceDTO, @PathVariable Long id) {
        try{
            Invoice updatedInvoice = invoiceService.partialUpdateInvoice(id, invoiceDTO);
            return ResponseHandler.responseBuilder("Invoice Updated Successfully", HttpStatus.OK, updatedInvoice);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("Error Updating Invoice", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable Long id) {
            invoiceService.deleteInvoice(id);
            return ResponseHandler.responseBuilder("Invoice Deleted Successfully", HttpStatus.OK, null);
    }
}
