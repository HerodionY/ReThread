package com.reTheard.reThreard.service;

import com.reTheard.reThreard.model.Friendship;
import com.reTheard.reThreard.model.User;
import com.reTheard.reThreard.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    public Friendship createFollow(UUID userId, UUID friendId) {
        if (friendshipRepository.existsByUserIdAndFriendId(userId, friendId)) {
            throw new IllegalArgumentException("Follow relationship already exists");
        }
        Friendship friendship = new Friendship();
        friendship.setUser(new User(friendId)); // Assuming `User` has a constructor with ID
        friendship.setFriend(new User(friendId));
        friendship.setIsFollowed(true);
        return friendshipRepository.save(friendship);
    }

    public List<Friendship> getFollowedByUserId(UUID userId) {
        return friendshipRepository.findByUserId(userId);
    }

    public void unfollow(UUID userId, UUID friendId) {
        Optional<Friendship> friendship = friendshipRepository.findByUserId(userId)
                .stream()
                .filter(f -> f.getFriend().getId().equals(friendId))
                .findFirst();

        if (friendship.isEmpty()) {
            throw new IllegalArgumentException("Follow relationship not found");
        }
        friendshipRepository.delete(friendship.get());
    }
}
