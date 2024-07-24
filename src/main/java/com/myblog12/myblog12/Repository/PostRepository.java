package com.myblog12.myblog12.Repository;

import com.myblog12.myblog12.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
