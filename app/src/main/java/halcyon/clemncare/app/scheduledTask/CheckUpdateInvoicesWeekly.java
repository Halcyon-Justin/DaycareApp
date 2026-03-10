package halcyon.clemncare.app.scheduledTask;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import halcyon.clemncare.app.dto.InvoiceDTO;
import halcyon.clemncare.app.enums.TaskStatus;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Task;
import halcyon.clemncare.app.service.implementation.FamilyServiceImpl;
import halcyon.clemncare.app.service.implementation.InvoiceServiceImpl;
import halcyon.clemncare.app.service.implementation.TaskServiceImpl;

@Component
public class CheckUpdateInvoicesWeekly {

    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @Autowired
    private FamilyServiceImpl familyServiceImpl;

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    // @PostConstruct
    // public void runScheduledTaskManually() {
    //     // You can call your scheduled task method here
    //     // This method will be executed once the bean is initialized
    //     CheckUpdateInvoicesWeekly();
    // }

    @Scheduled(cron = "0 0 0 * * MON") // Run Every Monday at Midnight
    public void CheckUpdateInvoicesWeekly() {
        // LocalDate previousDate = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        LocalDate nextDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        // Create new Task
        Task task = new Task();
        //Add name of Task
        task.setTaskName("Check_Update_Invoices_Weekly");
        // Set Status to IN_PROGRESS
        task.setStatus(TaskStatus.IN_PROGRESS);
        // Save -- This will create StartTime column entry.
        taskServiceImpl.createTask(task);
        // Changing the Status to FAILED or DONE will create EndTime column entry.

        // Gather all previous invoices
        // Get Invoices with Previous Monday's DueDate param
        // Find Invoices with InvoiceStatus as UNPAID
        // List<Invoice> LastWeeksUnpaidInvoices = invoiceServiceImpl.getAllInvoices().stream()
        //         .filter(invoice -> invoice.getDueDate().equals(previousDate)
        //                 && invoice.getStatus().equals(InvoiceStatus.UNPAID))
        //         .collect(Collectors.toList());

        // TODO:       
        // Send Notifications to Guardians that Invoice is UNPAID.

        // Create Invoices for Each Family with Active Children
        List<Family> familiesWithActiveChildren = familyServiceImpl.getAllFamilies().stream()
                .filter(family -> family.getChildren().stream().anyMatch(child -> child.isActive()))
                .collect(Collectors.toList());

        //For each Family, Calculate AmountDue and set InvoiceDTO to Save
        for (Family family : familiesWithActiveChildren) {
            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setFamily(family);
            invoiceDTO.setDueDate(nextDate);
            invoiceServiceImpl.createInvoice(invoiceDTO);
        }
        task.setStatus(TaskStatus.DONE);
        taskServiceImpl.updateTask(task.getId(), task);
    }
}
