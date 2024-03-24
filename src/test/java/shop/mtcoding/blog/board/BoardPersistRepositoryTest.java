package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired // DI
    private BoardPersistRepository boardPersistRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "제목수정1";

        // when
        Board board = boardPersistRepository.findById(id); //조회된 데이터! 영속화 됨!
        board.setTitle(title); //실제 쿼리가 날아간 건 아니고, pc에 있는 값을 변경한 것임
        //트랜젝션이 종료하면 업데이트가 날아가는데... 테스트에선 불가능하니까 em.flush 해줌
        em.flush();

    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardPersistRepository.findById(id);
        System.out.println("findById_test " + board);

        // then
        // org.assertj.core.api
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardPersistRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        // org.assertj.core.api
        //비교해서 검증해 보는 것! 틀리면 에러가 남
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void save_test() {
        // given
        Board board = new Board("제목5", "내용5", "ssar");

        // when
        boardPersistRepository.save(board);
        System.out.println("save_test : " + board);

        // then
    }
}