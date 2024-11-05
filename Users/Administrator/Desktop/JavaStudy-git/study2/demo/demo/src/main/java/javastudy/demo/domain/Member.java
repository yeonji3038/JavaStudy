package javastudy.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity //데이터베이스 테이블에 매핑
@Getter @Setter

public class Member {
    @Id @GeneratedValue //시퀀스 값 쓸때 사용
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //내장 타입 포함함
    private Address address;

    @OneToMany(mappedBy = "member") //1:다 -> 하나의 회원이 여러개 상품 주문
    private List<Order> orders = new ArrayList<>(); // 회원이 주문한 여러 주문들을 담는 리스트
    //orders 처음에 만들어놓고 컬렉션은 아예 바꾸지 말기


}