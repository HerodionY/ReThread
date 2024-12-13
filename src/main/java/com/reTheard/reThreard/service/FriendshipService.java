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
        // Check if a follow relationship already exists
        if (friendshipRepository.existsByUserIdAndFriendId(userId, friendId)) {
            throw new IllegalArgumentException("Follow relationship already exists");
        }

        // Create a new Friendship object and set user and friend
        User user = new User(userId); // Assuming a User constructor that accepts UUID
        User friend = new User(friendId); // Assuming a User constructor that accepts UUID
        Friendship friendship = new Friendship(user, friend, true); // Default to 'followed' status

        // Save and return the Friendship entity
        return friendshipRepository.save(friendship);
    }

    public List<Friendship> getFollowedByUserId(UUID userId) {
        // Return all friendships where the user is the one following
        return friendshipRepository.findByUserId(userId);
    }

    public void unfollow(UUID userId, UUID friendId) {
        // Find the Friendship entity to unfollow
        Optional<Friendship> friendship = friendshipRepository.findByUserId(userId)
                .stream()
                .filter(f -> f.getFriend().getId().equals(friendId))
                .findFirst();

        // If the Friendship is not found, throw an exception
        if (friendship.isEmpty()) {
            throw new IllegalArgumentException("Follow relationship not found");
        }

        // Delete the Friendship entity
        friendshipRepository.delete(friendship.get());
    }
}
