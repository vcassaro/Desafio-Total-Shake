package br.com.desafio.totalshake.application.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractDtoRequest<ID>{

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
        AbstractDtoRequest<?> other = (AbstractDtoRequest<?>) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
