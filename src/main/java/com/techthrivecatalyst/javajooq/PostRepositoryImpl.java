package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.pojos.Posts;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import static com.techthrivecatalyst.javajooq.generated.Tables.EMPLOYEE;
import static com.techthrivecatalyst.javajooq.generated.Tables.POSTS;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.field;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private static final com.techthrivecatalyst.javajooq.generated.tables.Posts P = POSTS;
    private static final com.techthrivecatalyst.javajooq.generated.tables.Employee E = EMPLOYEE;

    private final DSLContext dsl;


    public PostRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void insert(int userId, String content, String title) {
        dsl.insertInto(P)
                .set(P.CONTENT, content)
                .set(P.USER_ID, userId)
                .set(P.TITLE, title)
                .execute();
    }

    @Override
    public List<Posts> findAll() {
        return dsl.select()
                .from(P).
                fetchInto(Posts.class);
    }

    @Override
    public List<Posts> findPostsByUserEmail(String email) {
        return dsl.select(P)
                .from(P)
                .join(E).on(P.USER_ID.eq(E.ID))
                .where(E.EMAIL.eq(email))
                .fetchInto(Posts.class);
    }

    @Override
    public Integer findTotalPostsByUserId(int userId) {
        return dsl.select(count(P.ID))
                .from(P)
                .leftJoin(E).on(P.USER_ID.eq(E.ID))
                .where(P.USER_ID.eq(userId))
                .fetchOne(0, Integer.class);
    }

    @Override
    public Map<Integer, Integer> findPostCountsByUsers() {
        String postContent = "post_content";
        Result<Record2<Integer, Integer>> result = dsl
                .select(E.ID, count(P.ID).as(postContent))
                .from(E)
                .leftJoin(P).on(E.ID.eq(P.USER_ID))
                .groupBy(E.ID)
                .orderBy(field(postContent).desc())
                .fetch();
        return result.stream()
                .collect(Collectors.toMap(
                        Record2::value1, Record2::value2
                ));
    }
}
