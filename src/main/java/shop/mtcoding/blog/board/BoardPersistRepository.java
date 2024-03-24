package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;
    @Transactional
    public Board save(Board board) { //아직 저장 안 된 Board (순수한 우유)
        // 1. 비영속 객체
        em.persist(board);       //persist를 거쳐서 들어오면서 영속객체가 됨 (초코우유)

        // 2. board -> 영속 객체
        return board;       //이제는 return도 적을 필요가 없다
    }
}
