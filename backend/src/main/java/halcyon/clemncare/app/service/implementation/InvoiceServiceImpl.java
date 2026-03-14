package halcyon.clemncare.app.service.implementation;

import halcyon.clemncare.app.exception.FamilyNotFoundException;
import halcyon.clemncare.app.exception.InvoiceNotFoundException;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Invoice;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.InvoiceRepository;
import halcyon.clemncare.app.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        if (invoice.getFamily() != null && invoice.getFamily().getId() != null) {
            Family family = familyRepository.findById(invoice.getFamily().getId())
                    .orElseThrow(() -> new FamilyNotFoundException(
                            "Family with ID " + invoice.getFamily().getId() + " not found"));
            invoice.setFamily(family);
        }
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Long id, Invoice invoice) {
        Invoice existing = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice with ID " + id + " not found"));

        if (invoice.getDueDate() != null) existing.setDueDate(invoice.getDueDate());
        if (invoice.getPayDate() != null) existing.setPayDate(invoice.getPayDate());
        if (invoice.getAmount() != null) existing.setAmount(invoice.getAmount());
        if (invoice.getStatus() != null) existing.setStatus(invoice.getStatus());

        if (invoice.getFamily() != null && invoice.getFamily().getId() != null) {
            Family family = familyRepository.findById(invoice.getFamily().getId())
                    .orElseThrow(() -> new FamilyNotFoundException(
                            "Family with ID " + invoice.getFamily().getId() + " not found"));
            existing.setFamily(family);
        }

        return invoiceRepository.save(existing);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice with ID " + id + " not found"));
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new InvoiceNotFoundException("Invoice with ID " + id + " not found");
        }
        invoiceRepository.deleteById(id);
    }

    @Override
    public List<Invoice> getInvoicesByFamilyId(Long familyId) {
        return invoiceRepository.findByFamilyId(familyId);
    }
}