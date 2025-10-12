package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.pojos.Posts;
import java.util.List;

public interface PostRepository {

    void insert(int userId, String content, String title);

    List<Posts> findAll();

    List<Posts> findPostsByUserEmail(String email);
}
