package br.com.desafiojavaspringboot.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @CreationTimestamp
    private LocalDateTime dateCreate;
}
