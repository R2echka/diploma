package com.mew.diploma.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.mew.diploma.dto.Comments;
import com.mew.diploma.model.Comment;

@Mapper(componentModel = "spring")
public class CommentMapper {
    
    public Comments toComments(List<Comment> commentList){
        Comments comments = new Comments();
        comments.setResults(commentList);
        comments.setCount(commentList.size());
        return comments;
    }
}
