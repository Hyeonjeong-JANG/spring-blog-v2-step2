package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    @Transactional
    public void updateById(int id, BoardRequest.UpdateDTO requestDTO) {
        //여기서 조회 걸어줌!
        Board board = findById(id);
        board.update(requestDTO);   //보드에 있는 update 메소드
    } // 더티체킹

    @Transactional
    public void deleteById(int id) {
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Board findById(int id) {
        Board board = em.find(Board.class, id); //앞이 클래스, 뒤가 pk
        return board;
    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    @Transactional
    public Board save(Board board) { //아직 저장 안 된 Board (순수한 우유)
        // 1. 비영속 객체
        em.persist(board);       //persist를 거쳐서 들어오면서 영속객체가 됨 (초코우유)

        // 2. board -> 영속 객체
        return board;       //이제는 return도 적을 필요가 없다
    }
}
