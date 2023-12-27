package util;

import com.meli.socialmeli.entities.User;

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
}