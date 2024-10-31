package javastudy.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name ="delivery_id")
    private Long id;

//    @OneToOne(mappedBy = "delivery") // 1:1 매핑
//    private Order order;


    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @ Enumerated(EnumType.STRING) //STRING 무조건 이거 써주기!! 그래야 중간에 다른게 생겨도 밀리는게 없음! 1,2,3 이런식으로 들어감
    private DeliveryStatus status; //READY, COMP

}
