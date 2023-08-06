package com.springboot.blogAPI.service.impl;

import com.springboot.blogAPI.dto.DoubleIdObjectDto;
import com.springboot.blogAPI.dto.UserDto;
import com.springboot.blogAPI.errorExceptions.ResourceNotFoundException;
import com.springboot.blogAPI.model.Post;
import com.springboot.blogAPI.model.User;
import com.springboot.blogAPI.repository.UserRepository;
import com.springboot.blogAPI.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDto convertToDTO(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setProfilePic(user.getProfilePic());
        userDto.setPassword(user.getPassword());
        userDto.setFollowing(user.getFollowing());
        userDto.setFollowers(user.getFollowers());

        return userDto;
    }

    private User convertToEntity(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setProfilePic(userDto.getProfilePic());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFollowing(userDto.getFollowing());
        user.setFollowers(userDto.getFollowers());

        return user;
    }


    @Override
    public User createUser(UserDto userDto) {
        //convert DTO to entity
        User user = convertToEntity(userDto);
//        System.out.println(postDto);
        System.out.println(user);
        // Save new post
        User newUser = userRepository.save(user);

        //convert entity to PostDTO

        return newUser;
    }

    @Override
    public List<User> getAllFollowers(Long Id) {
        Optional<User> optUser = Optional.ofNullable(userRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", Id)));;
            List<String> followerIds = optUser.get().getFollowers();
            List<User> followerAccounts = new ArrayList<>();

            if (!followerIds.isEmpty()) {
                for (String followerId : followerIds) {
                    Optional<User> optFollowerUser = userRepository.findById(Long.valueOf(followerId));
                    if (optFollowerUser.isPresent()) {
                        User followerUser = optFollowerUser.get();
                        followerAccounts.add(followerUser);
                    }
                }
                return followerAccounts;

            } else {
                throw new ResourceNotFoundException("No Follower", "Id", Id);
            }

    }



    @Override
    public List<User> getAllFollowing(Long Id) {
        Optional<User> optUser = Optional.ofNullable(userRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", Id)));;
        List<String> followingIds = optUser.get().getFollowing();
        List<User> followingAccounts = new ArrayList<>();

        if (!followingIds.isEmpty()) {
            for (String followerId : followingIds) {
                Optional<User> optFollowingUser = userRepository.findById(Long.valueOf(followerId));
                if (optFollowingUser.isPresent()) {
                    User followerUser = optFollowingUser.get();
                    followingAccounts.add(followerUser);
                }
            }
            return followingAccounts;

        } else {
            throw new ResourceNotFoundException("No Follower", "Id", Id);
        }

    }


    @Override
    public UserDto getOneUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        System.out.println(user);
        return convertToDTO(user);
    }

    @Override
    public String followUser(DoubleIdObjectDto doubleId) {
        // id1 - followed user, id2 - follower
        Optional<User> optFollowedUser = userRepository.findById(Long.valueOf(doubleId.getId1()));
        Optional<User> optFollower = userRepository.findById(Long.valueOf(doubleId.getId2()));

        if (optFollowedUser.isEmpty() || optFollower.isEmpty()) {
            return "User 1 or User 2 not found";
        } else {
            User followedUser = optFollowedUser.get();
            User follower = optFollower.get();

            // add to follower list
            List<String> followerList = followedUser.getFollowers();
            if (followerList == null) {
                followerList = new ArrayList<>();
            }
            followerList.add(String.valueOf(follower.getId()));
            followedUser.setFollowers(followerList);

            // add to following list
            List<String> followingList = follower.getFollowing();
            if (followingList == null) {
                followingList = new ArrayList<>();
            }
            followingList.add(String.valueOf(followedUser.getId()));
            follower.setFollowing(followingList);

            userRepository.save(followedUser);
            userRepository.save(follower);
            return "Success";
        }

    }

    @Override
    public String unfollowUser(DoubleIdObjectDto doubleId) {

        // id1 - followed user, id2 - follower
        Optional<User> optFollowedUser = userRepository.findById(Long.valueOf(doubleId.getId1()));
        Optional<User> optFollower = userRepository.findById(Long.valueOf(doubleId.getId2()));

        if (optFollowedUser.isEmpty() || optFollower.isEmpty()) {
            return "User 1 or User 2 not found";
        } else {
            User followedUser = optFollowedUser.get();
            User follower = optFollower.get();

            // add to follower list
            List<String> followerList = followedUser.getFollowers();
            if (followerList == null) {
                followerList = new ArrayList<>();
            }
            followerList.remove(String.valueOf(follower.getId()));
            followedUser.setFollowers(followerList);

            // add to following list
            List<String> followingList = follower.getFollowing();
            if (followingList == null) {
                followingList = new ArrayList<>();
            }
            followingList.remove(String.valueOf(followedUser.getId()));
            follower.setFollowing(followingList);

            userRepository.save(followedUser);
            userRepository.save(follower);
            return "Success";
        }
    }

    @Override
    public String deleteUser(Long id) {
        return null;
    }
}
