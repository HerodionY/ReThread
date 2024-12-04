package com.reTheard.reThreard.repository;

import com.reTheard.reThreard.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {

    List<Friendship> findByUserId(UUID userId);

    List<Friendship> findByFriendId(UUID friendId);

    boolean existsByUserIdAndFriendId(UUID userId, UUID friendId);
}
