package test.hib.hib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class HibApplication {

    @Autowired
    Repo repo;
    public static void main(String[] args) {
        SpringApplication.run(HibApplication.class, args);
    }

/*
    @EventListener
    @Transactional
    public void startupListener(ApplicationReadyEvent event) {
        Master master = new Master();
        master.setName("Master name");
        master.setDetails(new HashSet<>(Arrays.asList(
                Detail.builder().master(master).lineNumber(1).text("Line1").build(),
                Detail.builder().master(master).lineNumber(2).text("Line2").build()
        )));

        repo.saveAndFlush(master);
        Master newMaster = repo.findById(master.getId()).get();
        HashSet<Detail> newDetails = new HashSet<>();
        for (Detail detail : newMaster.getDetails()) {
            if (detail.getLineNumber() != 2)
                newDetails.add(detail);
        }
        newMaster.getDetails().clear();
        newMaster.getDetails().addAll(newDetails);
        newMaster.getDetails().toArray(new Detail[]{})[0].setText("Line1 modified");
        newMaster.getDetails().add(Detail.builder().master(master).lineNumber(2).text("Line2 newly added").build());
        newMaster.getDetails().add(Detail.builder().master(master).lineNumber(3).text("Line3 new").build());
        repo.saveAndFlush(newMaster);
        final Master one = repo.findById(newMaster.getId()).get();

    }
*/

}
