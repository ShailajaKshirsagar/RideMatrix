package app.ridematrix.schedulers;

import app.ridematrix.converter.VisitorExcelDataMapper;
import app.ridematrix.dto.VisitorExcelDataDTO;
import app.ridematrix.entity.Visitors;
import app.ridematrix.helper.ExportVisitorsToExcel;
import app.ridematrix.repository.VisitorRepo;
import jakarta.persistence.Converter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ApplicationScheduler
{
    @Autowired
    private VisitorRepo visitorRepository;

    //scheduler to find visitduration field null and set it
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(fixedRate = 6000)
    public void setVisitDurationIfNull(){
        log.info("Scheduler 'setVisitDurationIfNull' started");
        try {
            List<Visitors> visitorsList = visitorRepository.findVisitorWithNullVisitDuration();
            log.info("Found {} visitors with null visit duration", visitorsList.size());

            visitorsList.forEach(v -> {
                v.setVisitDuration(v.calculateVisitDuration());
                visitorRepository.save(v);  // Save changes to DB
            });

            log.info("Updated visit duration for {} visitors", visitorsList.size());
        } catch (Exception e) {
            log.error("Error in 'setVisitDurationIfNull' scheduler", e);
        }
    }

    //Scheduler to save backup of visitor data in excel on local drive
    @Scheduled(cron = "0 0 23 * * *")
    public void visitorDataInExcel() {
        log.info("Scheduler 'visitorDataInExcel' started");
        try {
            List<Visitors> visitors = visitorRepository.findAll();
            log.info("Fetched {} visitors to export to Excel", visitors.size());
            List<VisitorExcelDataDTO> visitorExcelDataDTOList = visitors.stream()
                    .map(VisitorExcelDataMapper::toDto)
                    .collect(Collectors.toList());

            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
            String folderPath = "D:/visitors_log";
            File exportFolder = new File(folderPath);
            if (!exportFolder.exists()) {
                exportFolder.mkdirs();
                log.info("Created folder for export at '{}'", folderPath);
                //create folder
            }

            String filePath = folderPath + "/visitors_history_log" + currentDate + ".xlsx";
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                ExportVisitorsToExcel.visitorsToExcel(visitorExcelDataDTOList, fos);
            } // fos auto closed here

            System.out.println("Visitor Excel exported: " + filePath);
            log.info("Visitor Excel exported successfully to: {}", filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
