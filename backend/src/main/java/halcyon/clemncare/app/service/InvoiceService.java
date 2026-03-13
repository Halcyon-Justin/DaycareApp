package halcyon.clemncare.app.service;

import halcyon.clemncare.app.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice createInvoice(Invoice invoice);

    Invoice updateInvoice(Long id, Invoice invoice);

    Invoice getInvoice(Long id);

    List<Invoice> getAllInvoices();

    void deleteInvoice(Long id);

    List<Invoice> getInvoicesByFamilyId(Long familyId);
}