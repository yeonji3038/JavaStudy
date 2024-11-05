package javastudy.demo.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@DiscriminatorValue("B")
@Getter
@Setter
// extends Item 상속 받을때 사용
public class Book extends Item{
    private String author;
    private String  isbn;

}
