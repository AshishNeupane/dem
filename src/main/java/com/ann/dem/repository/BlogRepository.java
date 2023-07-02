package com.ann.dem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ann.dem.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

	Page<Blog> findAll(Pageable pageable);
}
