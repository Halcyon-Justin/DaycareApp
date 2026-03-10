package halcyon.clemncare.app.service.implementation;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.InvoiceDTO;
import halcyon.clemncare.app.exception.InvoiceNotFoundException;
import halcyon.clemncare.app.model.Invoice;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.InvoiceRepository;
import halcyon.clemncare.app.service.InvoiceCalculationService;
import halcyon.clemncare.app.service.InvoiceService;
import jakarta.transaction.Transactional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    FamilyRepository familyRepository;

    @Autowired
    InvoiceCalculationService invoiceCalculationService;

    @Override
    @Transactional
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {          
            Invoice invoice = new Invoice();
            invoice.setFamily(invoiceDTO.getFamily());
            invoice.setDueDate(invoiceDTO.getDueDate());
            Long amountDue = invoiceCalculationService.calculateAmountDue(invoiceDTO.getFamily().getId());
            invoice.setAmountDue(amountDue);
            return invoiceRepository.save(invoice);
        }

    @Override
    public Invoice updateInvoice(Long id, InvoiceDTO invoiceDTO) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            BeanUtils.copyProperties(invoiceDTO, existingInvoice);
            return invoiceRepository.save(existingInvoice);
        } else {
            throw new InvoiceNotFoundException("Invoice with ID: " + id + " does not exist");
        }
    }

    @Override
    public Invoice partialUpdateInvoice(Long id, InvoiceDTO invoiceDTO) {
        Invoice existinInvoice = invoiceRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(invoiceDTO, existinInvoice, getNullPropertyNames(invoiceDTO));
        return invoiceRepository.save(existinInvoice);
    }

    @Override
    public void deleteInvoice(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    @Override
    public Optional<Invoice> getInvoice(Long invoiceId) {
        return invoiceRepository.findById(invoiceId);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> findInvoicesByDueDate(Date dueDate) {
        return invoiceRepository.findByDueDate(dueDate);
    }

    @Override
    public List<Invoice> findInvoicesByFamilyId(Long familyId) {
        return invoiceRepository.findByFamilyId(familyId);
    }

    private String[] getNullPropertyNames(InvoiceDTO invoiceDTO) {
        final BeanWrapper src = new BeanWrapperImpl(invoiceDTO);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
