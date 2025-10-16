package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.pojos.Posts;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryIntegrationTest extends BaseIntegrationTest {

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

    @Test
    void shouldFindPostCountByUser() {
        userRepository.insert("John Doe", "john.doe@example.com");
        postRepository.insert(1, "This is the first content", "First Post Title");
        postRepository.insert(1, "This is the second content", "Second Post Title");

        assertThat(postRepository.findTotalPostsByUserId(1)).isEqualTo(2);
    }

    @Test
    void shouldFindPostCountsPerUser() {
        userRepository.insert("John Doe", "john.doe@example.com");
        userRepository.insert("Jane Doe", "jane.doe@example.com");

        postRepository.insert(1, "This is the first content", "Post Title 1");
        postRepository.insert(1, "This is the second content", "Post Title 2");
        postRepository.insert(2, "This is the third content", "Post Title 3");
        postRepository.insert(2, "This is the fourth content", "Post Title 4");
        postRepository.insert(2, "This is the fifth content", "Post Title 5");

        Map<Integer, Integer> countByUser = postRepository.findPostCountsByUsers();

        assertThat(countByUser).hasSize(2);
        assertThat(countByUser.get(1)).isEqualTo(2);
        assertThat(countByUser.get(2)).isEqualTo(3);
    }
}
