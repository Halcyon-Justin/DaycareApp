package halcyon.clemncare.app.service.implementation;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.ReportCardDTO;
import halcyon.clemncare.app.exception.ChildNotFoundException;
import halcyon.clemncare.app.exception.ReportCardNotFoundException;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.ReportCardRepository;
import halcyon.clemncare.app.service.ReportCardService;

@Service
public class ReportCardServiceImpl implements ReportCardService {


    private ChildRepository childRepository;
    private ReportCardRepository reportCardRepository;

    public ReportCardServiceImpl(ChildRepository childRepository, ReportCardRepository reportCardRepository) {
        this.childRepository = childRepository;
        this.reportCardRepository = reportCardRepository;
    }

    @Override
    public ReportCard createReportCard(ReportCardDTO reportCardDTO) {
        try {
    Long childId = reportCardDTO.getChildId();
    Optional<Child> optionalChild = childRepository.findById(childId);

    if (optionalChild.isPresent()) {
        Child child = optionalChild.get();
        ReportCard reportCard = mapReportCardToDTO(reportCardDTO, child);
        return reportCardRepository.save(reportCard);
    } else {
        throw new ChildNotFoundException("Child not found. Can not map Report Card to Child.");
    }
} catch (ChildNotFoundException e) {
    throw e; // Re-throw the ChildNotFoundException
} catch (Exception e) {
    // Log the exception for further investigation
    e.printStackTrace();
    throw new RuntimeException("Error creating report card", e);
}

    }

    @Override
    public ReportCard updateReportCard(Long id, ReportCardDTO reportCardDTO) {
        Optional<ReportCard> optionalReportCard = reportCardRepository.findById(id);
        if (optionalReportCard.isPresent()) {
            ReportCard existingReportCard = optionalReportCard.get();
            BeanUtils.copyProperties(reportCardDTO, existingReportCard);
            return reportCardRepository.save(existingReportCard);
        } else {
            throw new ReportCardNotFoundException("Report Card with ID " + id + " not found");
        }
    }

    @Override
    public ReportCard partialUpdateReportCard(Long id, ReportCardDTO reportCardDTO) {
        Optional<ReportCard> optionalReportCard = reportCardRepository.findById(id);
        if (optionalReportCard.isPresent()) {
            ReportCard existingReportCard = optionalReportCard.get();
            BeanUtils.copyProperties(reportCardDTO, existingReportCard,getNullPropertyNames(reportCardDTO));
            return reportCardRepository.save(existingReportCard);
        } else {
            throw new ReportCardNotFoundException("Report Card with ID " + id + " not found");
        }
    }

    @Override
    public void deleteReportCard(Long reportCardId) {
        reportCardRepository.deleteById(reportCardId);
    }

    @Override
    public Optional<ReportCard> getReportCard(Long reportCardId) {
        return reportCardRepository.findById(reportCardId);
    }

    @Override
    public Page<ReportCard> getReportCardsByChildId(Long childId, Pageable pageable) {
        return reportCardRepository.findByChildId(childId, pageable);
    }

    private ReportCard mapReportCardToDTO(ReportCardDTO reportCardDTO, Child child) {
        ReportCard reportCard = new ReportCard();
        reportCard.setChildId(child.getId());
        reportCard.setHasNapped(reportCardDTO.isHasNapped());
        reportCard.setNotes(reportCardDTO.getNotes());
        reportCard.setSendTo(getGuardianEmails(child.getFamily().getGuardians()));

        return reportCard;
    }

    private List<String> getGuardianEmails(List<Guardian> guardians) {
        return guardians.stream()
                .map(Guardian::getEmailAddress)
                .collect(Collectors.toList());
    }

    private String[] getNullPropertyNames(ReportCardDTO reportCardDTO) {
        final BeanWrapper src = new BeanWrapperImpl(reportCardDTO);
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
