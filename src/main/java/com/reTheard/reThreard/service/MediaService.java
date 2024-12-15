package com.reTheard.reThreard.service;

import com.reTheard.reThreard.model.Media;
import com.reTheard.reThreard.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MediaService {
    
    @Autowired
    private MediaRepository mediaRepository;

    // Save media to the database
    public Media saveMedia(Media media) {
        return mediaRepository.save(media);
    }

    // Get media by post ID (UUID type)
    public List<Media> getMediaByPostId(UUID postId) {
        return mediaRepository.findByPostId(postId);
    }
}
