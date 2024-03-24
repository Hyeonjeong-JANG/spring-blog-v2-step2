package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;

    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    //다른 곳에서 재사용하려면 DTO 이름을 적을 수 없다!
    public void update(BoardRequest.UpdateDTO reqDTO) {
        this.title = reqDTO.getTitle();
        this.content = reqDTO.getContent();
        this.username = reqDTO.getUsername();
    }
}
