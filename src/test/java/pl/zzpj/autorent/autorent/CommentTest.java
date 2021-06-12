package pl.zzpj.autorent.autorent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.repositories.CommentRepository;
import pl.zzpj.autorent.autorent.model.Comment;
import pl.zzpj.autorent.autorent.services.CarService;
import pl.zzpj.autorent.autorent.services.CommentService;

import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {AutorentApplication.class})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CarService carService;

    Comment comment;
    Car car;

    @Before
    public void setUp(){
        car = new Car("test", "test", "test");
        carService.addCar(car);
        comment = new Comment("testC", car.getId(), "test", new Date(System.currentTimeMillis()) , "test");
        commentService.addComment(comment);
        carService.addComment(comment.getCarId(), comment);
    }

    @Test
    public void addCommentTest(){
        assertThat(commentRepository.get(comment.getId()).get().equals(comment));
    }

    @Test
    public void deleteCommentTest(){
        commentService.deleteComment(comment.getId());
        assertThat(commentRepository.get(comment.getId()).get() == null);
    }

    @Test
    public void deleteCarAndCommentsTest(){
        carService.deleteCar(car.getId());
        assertThat(commentRepository.get(comment.getId()).get() == null);
    }

    @Test
    public void getAllCommentsTest() throws IOException, InterruptedException {
        assertThat(commentService.getAllComments().size() !=0 );
    }

//    @Test
//    public void  getAllCommentsByCarIdTest() throws IOException, InterruptedException {
//        assertThat(commentService.getAllCommentsByCarId(car.getId()).size() != 0);
//    }


}