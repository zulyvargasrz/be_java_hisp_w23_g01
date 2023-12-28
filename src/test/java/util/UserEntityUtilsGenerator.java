package util;

import com.meli.socialmeli.entities.Post;
import com.meli.socialmeli.entities.Product;
import com.meli.socialmeli.entities.User;

import java.time.LocalDate;
import java.util.List;

public class UserEntityUtilsGenerator {

    public static User getUserWithThreeFollowers(){
        User userFollowerOne = new User(200000,"A-Follower One", null, null, null);
        User userFollowerTwo = new User(300000,"B-Follower Two",null, null, null);
        User userFollowerThree = new User(400000,"Z-Follower Three",null, null, null);
        User user =  new User(100000, "User with followers",
                List.of(userFollowerTwo,userFollowerOne, userFollowerThree), null, null);
        userFollowerOne.setFollowed(List.of(user));
        userFollowerTwo.setFollowed(List.of(user));
        userFollowerThree.setFollowed(List.of(user));
        return user;
    }
    public static User getUserWithThreeFollowed(){
        User userFollowerOne = new User(200000,"A-Follower One", null, null, null);
        User userFollowerTwo = new User(300000,"B-Follower Two",null, null, null);
        User userFollowerThree = new User(400000,"Z-Follower Three",null, null, null);
        User user =  new User(100000, "User with followers",
                null, List.of(userFollowerTwo,userFollowerOne, userFollowerThree), null);
        userFollowerOne.setFollowers(List.of(user));
        userFollowerTwo.setFollowers(List.of(user));
        userFollowerThree.setFollowers(List.of(user));
        return user;
    }

    public static User getUserFollwingSellers() {
        User seller1 = new User(2, "seller1", List.of(), List.of(), List.of());
        User seller2 = new User(3, "seller2", List.of(), List.of(), null);
        List sellers = List.of(seller1, seller2);

        Post post1 = new Post(
                LocalDate.of(2023, 12, 20),
                new Product(
                        1,
                        "Compu Gamer",
                        "Compu",
                        "MSI",
                        "Black",
                        "Compu buena"
                ),
                1,
                10000.0,
                1
        );

        Post post2 = new Post(
                LocalDate.of(2023, 12, 11),
                new Product(
                        2,
                        "Silla Ergonomica",
                        "Silla",
                        "Coleman",
                        "Azul",
                        "Buena silla"
                ),
                1,
                154454.4,
                2
        );

        List<Post> posts = List.of(post1, post2);

        seller2.setPosts(posts);

        User user = new User(1,"User", sellers, List.of(), List.of());

        return user;
    }
}