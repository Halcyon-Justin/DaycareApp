package halcyon.clemncare.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import halcyon.clemncare.app.dto.ReportCardDTO;
import halcyon.clemncare.app.model.ReportCard;

public interface ReportCardService {
    public ReportCard createReportCard(ReportCardDTO reportCardDTO);

    public ReportCard updateReportCard(Long id, ReportCardDTO reportCardDTO);

    public ReportCard partialUpdateReportCard(Long id, ReportCardDTO reportCardDTO);

    public void deleteReportCard(Long reportCardId);

    public Optional<ReportCard> getReportCard(Long reportCardId);

    public Page<ReportCard> getReportCardsByChildId(Long childId, Pageable pageable);

}
