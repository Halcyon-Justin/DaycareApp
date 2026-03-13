package halcyon.clemncare.app.controller;

import halcyon.clemncare.app.dto.InvoiceDTO;
import halcyon.clemncare.app.mapper.InvoiceMapper;
import halcyon.clemncare.app.model.Invoice;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @GetMapping("/")
    public ResponseEntity<Object> getAllInvoices() {
        List<InvoiceDTO> dtos = invoiceService.getAllInvoices()
                .stream().map(invoiceMapper::toDTO).collect(Collectors.toList());
        return ResponseHandler.responseBuilder("All Invoices", HttpStatus.OK, dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInvoice(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoice(id);
        return ResponseHandler.responseBuilder("Requested Invoice", HttpStatus.OK, invoiceMapper.toDTO(invoice));
    }

    @GetMapping("/family/{familyId}")
    public ResponseEntity<Object> getInvoicesByFamily(@PathVariable Long familyId) {
        List<InvoiceDTO> dtos = invoiceService.getInvoicesByFamilyId(familyId)
                .stream().map(invoiceMapper::toDTO).collect(Collectors.toList());
        return ResponseHandler.responseBuilder("Invoices for Family", HttpStatus.OK, dtos);
    }

    @PostMapping
    public ResponseEntity<Object> createInvoice(@RequestBody InvoiceDTO dto) {
        Invoice invoice = invoiceMapper.toEntity(dto);
        Invoice saved = invoiceService.createInvoice(invoice);
        return ResponseHandler.responseBuilder("Invoice Created", HttpStatus.CREATED, invoiceMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO dto) {
        Invoice invoice = invoiceMapper.toEntity(dto);
        Invoice updated = invoiceService.updateInvoice(id, invoice);
        return ResponseHandler.responseBuilder("Invoice Updated", HttpStatus.OK, invoiceMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseHandler.responseBuilder("Invoice Deleted", HttpStatus.OK, null);
    }
}