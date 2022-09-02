package br.com.desafio.totalshake.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractModel<ID>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractModel<?> other = (AbstractModel<?>) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
