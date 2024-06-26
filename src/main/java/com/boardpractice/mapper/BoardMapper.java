package com.boardpractice.mapper;

import com.boardpractice.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO board
            (title, content, writer)
            VALUES (#{title}, #{content}, #{writer})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Board board);

    @Select("""
            SELECT *
            FROM board
            WHERE id = #{id}
            """)
    Board selectById(Integer id);

    @Select("""
            SELECT *
            FROM board
            ORDER BY id DESC
            LIMIT 10
            """)
    List<Board> selectAll(Integer page);

    @Update("""
            UPDATE board
            SET
                title = #{title},
                content = #{content},
                modified = NOW()
            WHERE id = #{id}
            """)
    int update(Board board);

    @Delete("""
            DELETE FROM board
            WHERE id = #{id}
            """)
    int deleteById(Integer id);

    @Select("""
            SELECT COUNT(*) FROM board
            """)
    int countAll();

    @Select("""
            SELECT *
            FROM board
            ORDER BY id DESC
            LIMIT 10 OFFSET #{offset}
            """)
    List<Board> selectAllByPage(int offset);
}
