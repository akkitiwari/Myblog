package com.myblog12.myblog12.Service;

import com.myblog12.myblog12.Payload.CommentDto;

public interface CommentService {


    CommentDto createPost(CommentDto commentDto,long postId);

    void deleteComment(Long id);

   CommentDto updateComment(Long id, CommentDto commentDto, Long postId);
}
