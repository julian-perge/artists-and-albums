package com.wecancodeit.julian.artistsandalbums.repository;

import org.springframework.data.repository.CrudRepository;

import com.wecancodeit.julian.artistsandalbums.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {}
