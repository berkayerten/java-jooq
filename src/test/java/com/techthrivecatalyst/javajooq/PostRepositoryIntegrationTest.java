package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.pojos.Posts;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldInsertAndFetchPosts() {
        userRepository.insert("John Doe", "john.doe@example.com");
        postRepository.insert(1, "This is the content", "Post Title");

        var posts = postRepository.findAll();
        assertThat(posts).hasSize(1);
        var post = posts.getFirst();
        assertThat(post.getUserId()).isEqualTo(1);
        assertThat(post.getContent()).isEqualTo("This is the content");
        assertThat(post.getTitle()).isEqualTo("Post Title");
    }

    @Test
    void shouldFindPostsThatBelongToAUserEmail() {
        userRepository.insert("John Doe", "john.doe@example.com");
        postRepository.insert(1, "This is the first content", "First Post Title");
        postRepository.insert(1, "This is the second content", "Second Post Title");

        List<Posts> posts = postRepository.findPostsByUserEmail("john.doe@example.com");
        assertThat(posts).hasSize(2);
    }
}
