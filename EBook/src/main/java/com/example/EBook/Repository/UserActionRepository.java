package com.example.EBook.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.EBook.entity.UserAction;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction, Long> {
	List<UserAction> findByUserId(@Param("userId") Long userId);
	
	UserAction findByUserIdAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Long categoryId);
}
