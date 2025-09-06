package app.ridematrix.schedulers;

import app.ridematrix.entity.Visitors;
import app.ridematrix.repository.VisitorRepo;
import jakarta.persistence.Converter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationScheduler
{
    @Autowired
    private VisitorRepo visitorRepository;

    //scheduler to find visitduration field null and set it
    @Transactional
    @Scheduled(fixedRate = 6000)
    public void setVisitDurationIfNull(){
        List<Visitors> visitorsList = visitorRepository.findVisitorWithNullVisitDuration();
        visitorsList.forEach(v -> {
            v.setVisitDuration(v.calculateVisitDuration());
            visitorRepository.save(v);  // Save changes to DB
        });
    }
}
