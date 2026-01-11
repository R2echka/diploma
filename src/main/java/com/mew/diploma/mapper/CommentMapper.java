package com.mew.diploma.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mew.diploma.dto.CommentDTO;
import com.mew.diploma.dto.CommentsDTO;
import com.mew.diploma.model.Comment;
import com.mew.diploma.model.Image;
import com.mew.diploma.model.User;
import com.mew.diploma.repository.ImageRepository;
import com.mew.diploma.repository.UserRepository;

@Component
public class CommentMapper {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public CommentMapper(UserRepository userRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }
    
    public CommentsDTO toComments(List<Comment> commentList){
        CommentsDTO comments = new CommentsDTO();
        List<CommentDTO> dtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            dtoList.add(toDTO(comment));
        }
        comments.setResults(dtoList);
        comments.setCount(commentList.size());
        return comments;
    }

    public CommentDTO toDTO(Comment comment){
        User author = userRepository.findById((long)comment.getAuthorId());
        Image image = imageRepository.findById(author.getImageId()).orElse(null);

        CommentDTO dto = new CommentDTO();

        if(image != null){
            String base64Image = java.util.Base64.getEncoder()
                .encodeToString(image.getData());
            String mediaType = image.getMediaType();
            dto.setAuthorImage("data:" + mediaType + ";base64," + base64Image);
        }


        dto.setAuthor(author.getId());
        dto.setAuthorFirstName(author.getFirstName());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setPk(comment.getId());
        dto.setText(comment.getText());

        return dto;
    }
}
