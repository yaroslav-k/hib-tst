package test.hib.hib;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    @JoinColumn(name = "master_id",nullable = false, referencedColumnName = "id")
    private Master master;
    @Column(name="lineNumber")
    private long lineNumber;
    String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Detail)) return false;

        Detail detail = (Detail) o;

        return new EqualsBuilder()
                .append(getLineNumber(), detail.getLineNumber())
                .append(getId(), detail.getId())
                .append(getText(), detail.getText())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getLineNumber())
                .append(getText())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", lineNumber=" + lineNumber +
                ", text='" + text + '\'' +
                '}';
    }
}
