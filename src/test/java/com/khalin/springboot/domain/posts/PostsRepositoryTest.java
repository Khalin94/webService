package com.khalin.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    public void postSave() throws Exception{
        //given
        String title = "테스트 타이틀";
        String content = "테스트 내용";

        postsRepository.save(Posts.builder().title(title).content(content).author("test@test.com").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void registerBaseTime() throws Exception{
        LocalDateTime date = LocalDateTime.of(2020, 2, 8, 0, 0, 0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate : "+ posts.getCreatedDate() + "modifiedDate : " + posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(date);
        assertThat(posts.getModifiedDate()).isAfter(date);

    }
}
