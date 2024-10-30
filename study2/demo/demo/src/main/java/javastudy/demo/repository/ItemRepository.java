package javastudy.demo.repository;

import jakarta.persistence.EntityManager;
import javastudy.demo.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;
    public void save(Item item) { //상품 저장,update랑 비슷
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    //아이템을 하나 조회
    public Item findOne(Long id) {

        return em.find(Item.class, id);
    }
    //여러개 찾을때
    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }
}