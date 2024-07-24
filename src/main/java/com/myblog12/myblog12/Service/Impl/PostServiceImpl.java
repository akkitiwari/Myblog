package com.myblog12.myblog12.Service.Impl;


import com.myblog12.myblog12.Entity.Post;
import com.myblog12.myblog12.Exception.ResourceNotFoundException;
import com.myblog12.myblog12.Payload.PostDto;
import com.myblog12.myblog12.Repository.PostRepository;
import com.myblog12.myblog12.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    private ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost= postRepository.save(post);


        PostDto dto = mapToDto(savedPost);
        return dto;
    }
    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(//here we use the concept of java 8
                ()->new ResourceNotFoundException("Post not found with id:"+id)

        );
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        return dto;
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort =(sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        return dtos;
    }

    PostDto mapToDto(Post post){

        PostDto dto= modelMapper.map(post, PostDto.class);

        return dto;

    }
    Post mapToEntity(PostDto postDto)  {
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

}


