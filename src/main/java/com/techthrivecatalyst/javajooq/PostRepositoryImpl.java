package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.pojos.Posts;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.techthrivecatalyst.javajooq.generated.Tables.APP_USER;
import static com.techthrivecatalyst.javajooq.generated.Tables.POSTS;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private static final com.techthrivecatalyst.javajooq.generated.tables.Posts P = POSTS;
    private static final com.techthrivecatalyst.javajooq.generated.tables.AppUser AU = APP_USER;

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

    public List<Posts> findAll() {
        return dsl.select()
                .from(P).
                fetchInto(Posts.class);
    }

    public List<Posts> findPostsByUserEmail(String email) {
        return dsl.select(POSTS.ID, POSTS.TITLE, POSTS.CONTENT, POSTS.USER_ID, POSTS.CREATED_AT)
                .from(P)
                .join(AU).on(P.USER_ID.eq(AU.ID))
                .where(AU.EMAIL.eq(email))
                .fetchInto(Posts.class);
    }

}
