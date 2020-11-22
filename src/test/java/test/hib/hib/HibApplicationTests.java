package test.hib.hib;

import org.apache.catalina.session.PersistentManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class HibApplicationTests {
    @Autowired
    Repo repo;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    void testMasterDetail() {
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
        assertTrue(one.getDetails().stream().map(Detail::getText).anyMatch(s -> s.equals("Line1 modified")));
        assertTrue(one.getDetails().stream().map(Detail::getText).anyMatch(s -> s.equals("Line2 newly added")));
        assertTrue(one.getDetails().stream().map(Detail::getText).anyMatch(s -> s.equals("Line3 new")));

    }

}
