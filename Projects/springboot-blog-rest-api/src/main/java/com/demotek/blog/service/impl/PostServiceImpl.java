package com.demotek.blog.service.impl;

import com.demotek.blog.dto.PostDto;
import com.demotek.blog.dto.PostResponse;
import com.demotek.blog.entity.Post;
import com.demotek.blog.exception.ResourceNotFoundException;
import com.demotek.blog.repository.PostRepository;
import com.demotek.blog.service.PostService;
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
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper=mapper;

    }

    @Override
    public PostDto creatPost(PostDto postDto) {

        Post post = mapToEntity(postDto);


        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDto(newPost);

        return postResponse;
    }


    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                    Sort.by(sortBy).descending();

        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Post> postPages = postRepository.findAll(pageable);

        //get the content of post
        List<Post> postList = postPages.getContent();

        List<PostDto> listOfPost = postList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(listOfPost);
        postResponse.setPageNo(pageNo);
        postResponse.setPageSize(pageSize);
        postResponse.setTotalElements(postPages.getNumberOfElements());
        postResponse.setTotalSize(postPages.getSize());
        postResponse.setLast(postPages.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));


        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedResponse = postRepository.save(post);
        return mapToDto(updatedResponse);
    }

    @Override
    public void deleteById(long id) {
        Post deletePost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(deletePost);
    }


    //convert entity to dto
    private PostDto mapToDto(Post post) {
        PostDto  postDto = mapper.map(post,PostDto.class);

//        PostDto postDto = new PostDto();
//
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());

        return postDto;
    }

    // convert dto entity
    private Post mapToEntity(PostDto postDto) {

        Post post =  mapper.map(postDto,Post.class);

//        Post post = new Post();
//
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
}
