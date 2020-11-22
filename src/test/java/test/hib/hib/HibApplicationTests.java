package test.hib.hib;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                Detail.builder().lineNumber(1).text("Line1").build(),
                Detail.builder().lineNumber(2).text("Line2").build()
        )));

        repo.saveAndFlush(master);
        Master newMaster = repo.getOne(master.getId());
        newMaster.getDetails().toArray(new Detail[]{})[0].setText("Line1 modified");
        newMaster.getDetails().toArray(new Detail[]{})[1].setText("Line2 modified");
        newMaster.getDetails().add(Detail.builder().lineNumber(3).text("Line3 new").build());
        repo.saveAndFlush(newMaster);
        final Master one = repo.findById(newMaster.getId()).get();
        assertTrue(one.getDetails().stream().map(Detail::getText).anyMatch(s -> s.equals("Line1 modified")));
        assertTrue(one.getDetails().stream().map(Detail::getText).anyMatch(s -> s.equals("Line2 modified")));
        assertTrue(one.getDetails().stream().map(Detail::getText).anyMatch(s -> s.equals("Line3 new")));

    }

}
