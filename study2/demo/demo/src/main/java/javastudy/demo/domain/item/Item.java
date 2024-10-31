package javastudy.demo.domain.item;

import jakarta.persistence.*;
import javastudy.demo.domain.Category;
import javastudy.demo.exception.NotEnoughStockException; //예외생성 -> 재고수량 줄이기 위해
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//SINGLE_TABLE 한 테이블에 다 때려 넣기
@Getter @Setter
@DiscriminatorColumn(name = "dtype")
//추가 클래스는 abstract class 써줌
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //비지니스 로직// 재고 수량 조절
    //==비즈니스 로직==//

    //재고 수량 늘리기
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    //재고 수량 줄이기
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}

