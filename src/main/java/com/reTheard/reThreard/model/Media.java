package com.reTheard.reThreard.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Generates UUID automatically
    private UUID id;

    private String imageName;
    private String imageType;
    private String imageData; // Base64 encoded data

    @ManyToOne
    @JoinColumn(name = "post_id")  // Establishes the foreign key relationship to Post
    @JsonBackReference  // Prevents circular references during serialization
    private Post post;


    // Default constructor
    public Media() {}

    // Constructor with parameters
    public Media(String imageName, String imageType, String imageData, Post post) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageData = imageData;
        this.post = post;
    }
}
