package com.myblog12.myblog12.Service.Impl;

import com.myblog12.myblog12.Entity.Comment;
import com.myblog12.myblog12.Entity.Post;
import com.myblog12.myblog12.Exception.ResourceNotFoundException;
import com.myblog12.myblog12.Payload.CommentDto;
import com.myblog12.myblog12.Repository.CommentRepository;
import com.myblog12.myblog12.Repository.PostRepository;
import com.myblog12.myblog12.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRespository;



    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRespository,ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRespository = postRespository;
        this.modelMapper =modelMapper;
    }

    @Override
    public CommentDto createPost(CommentDto commentDto, long postId) {

        Post post = postRespository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("Post not found with id:" +postId)
        );

        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setEmail(commentDto.getEmail());
        comment.setPost(post);
        Comment commentSave = commentRepository.save(comment);



        CommentDto  dto= new CommentDto();
        dto.setId(commentSave.getId());
        dto.setText(commentSave.getText());
        dto.setEmail(commentSave.getEmail());
        return dto;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

//    @Override
//    public Comment updateComment(Long id, Comment comment) {
//        commentRepository.findById(id).orElseThrow(
//                ()->new ResourceNotFoundException("Comment not found for id: "+id)
//
//        );
//
//        Comment comment = modelMapper.map(commentDto, Comment.class);
//        Comment  updatedComment = commentRepository.save(comment);
//
//
//        CommentDto dto = modelMapper.map(updatedComment, CommentDto.class);
//          return dto;
//
//    }

    @Override
    public CommentDto updateComment(Long id, CommentDto commentDto, Long postId) {
        Post post = postRespository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found for id: " + id)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found for id: " + id)

        );

        Comment c = mapToEntity(commentDto) ;
            c.setId(comment.getId());
            c.setPost(post);
            Comment savedComment = commentRepository.save(c);


            return mapToDto(savedComment);
        }


   CommentDto  mapToDto(Comment comment){
       CommentDto dto = modelMapper.map(comment, CommentDto.class);
       return dto;
   }
   Comment mapToEntity(CommentDto commentDto){
       Comment comment = modelMapper.map(commentDto, Comment.class);
       return comment;
   }

}
