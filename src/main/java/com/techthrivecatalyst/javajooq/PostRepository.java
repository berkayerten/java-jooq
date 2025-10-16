package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.pojos.Posts;
import java.util.List;
import java.util.Map;

public interface PostRepository {

    void insert(int userId, String content, String title);

    List<Posts> findAll();

    List<Posts> findPostsByUserEmail(String email);

    Integer findTotalPostsByUserId(int userId);

    Map<Integer, Integer> findPostCountsByUsers();
}
