package test.hib.hib;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Yaroslav V. Khazanov
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(unique = true, columnList = "master_id, lineNumber")
},
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"master_id", "lineNumber"})
})
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;
    @Column(name="lineNumber")
    private long lineNumber;
    String text;


}
