package com.ecommerce.comment.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("CommentRepositoryCustom")
public interface CommentRepositoryCustom {
}
