package test.hib.hib;

import lombok.Data;
import org.springframework.cglib.core.GeneratorStrategy;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author Yaroslav V. Khazanov
 **/
@Entity
@Data
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "master", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    Set<Detail> details;
}
