package halcyon.clemncare.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import halcyon.clemncare.app.dto.InvoiceDTO;
import halcyon.clemncare.app.model.Invoice;

public interface InvoiceService {

    public Invoice createInvoice(InvoiceDTO invoiceDTO);

    public Invoice updateInvoice(Long id, InvoiceDTO invoiceDTO);

    public Invoice partialUpdateInvoice(Long id, InvoiceDTO invoiceDTO);

    public void deleteInvoice(Long invoiceId);

    public Optional<Invoice> getInvoice(Long invoiceId);

    public List<Invoice> getAllInvoices();

    public List<Invoice> findInvoicesByDueDate(Date dueDate);

    public List<Invoice> findInvoicesByFamilyId(Long familyId);

}
