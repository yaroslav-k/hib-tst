package test.hib.hib;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    @OneToMany(mappedBy = "master", fetch = FetchType.EAGER,cascade = {CascadeType.ALL}, orphanRemoval = true)
            @Fetch(FetchMode.JOIN)
    Set<Detail> details;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Master)) return false;

        Master master = (Master) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(getId(), master.getId())
                .append(getName(), master.getName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Master{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
