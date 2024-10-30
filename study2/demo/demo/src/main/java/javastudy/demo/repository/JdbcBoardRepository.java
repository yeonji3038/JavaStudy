package javastudy.demo.repository;

import javastudy.demo.domain.board.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository // Spring의 컴포넌트 스캔에 의해 이 클래스가 DAO로 인식되도록 함
public class JdbcBoardRepository implements BoardRepository{

    private final JdbcTemplate jdbcTemplate; // JDBC를 통한 데이터베이스 접근을 위한 템플릿

    // 생성자에서 DataSource를 주입받아 JdbcTemplate을 초기화
    public JdbcBoardRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Board save(Board board) {
        // SimpleJdbcInsert를 사용하여 INSERT 쿼리를 수행.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("id"); // 자동 생성된 키 컬럼 설정

        // Board 객체의 필드 값을 맵에 저장.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("author", board.getAuthor());
        parameters.put("title", board.getTitle());
        parameters.put("content", board.getContent());

        // INSERT 쿼리를 실행하고 생성된 키를 가져옴.
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        board.setId(key.longValue()); // 생성된 키를 Board 객체에 설정

        return board; // 저장된 Board 객체를 반환
    }

    @Override
    public List<Board> findAll() {
        // 모든 게시물을 조회하는 쿼리 실행
        return jdbcTemplate.query("select * from board", boardRowMapper());
    }

    @Override
    public Optional<Board> findById(Long id) {
        // ID로 게시물을 조회하는 쿼리 실행
        List<Board> result = jdbcTemplate.query("select * from board where id = ?", boardRowMapper(), id);
        return result.stream().findAny(); // 결과가 있을 경우 Optional로 반환
    }

    @Override
    public Board updatePost(Board board) {
        // 게시물을 업데이트하는 쿼리 실행
        jdbcTemplate.update("update board set author = ?, title = ?, content = ? where id = ?",
                board.getAuthor(), board.getTitle(), board.getContent(), board.getId());
        return board; // 업데이트된 Board 객체를 반환
    }

    @Override
    public void deletePost(Long id) {
        // ID로 게시물을 삭제하는 쿼리 실행
        jdbcTemplate.update("delete from board where id = ?", id);
    }

    // RowMapper를 정의하여 ResultSet을 Board 객체로 매핑하는 메서드
    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getLong("id")); // ID 매핑
            board.setAuthor(rs.getString("author")); // 저자 매핑
            board.setContent(rs.getString("content")); // 내용 매핑
            board.setTitle(rs.getString("title")); // 제목 매핑
            return board; // 매핑된 Board 객체 반환
        };
    }
}
