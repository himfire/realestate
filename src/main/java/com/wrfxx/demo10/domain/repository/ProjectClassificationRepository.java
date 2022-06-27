package com.wrfxx.demo10.domain.repository;

import com.wrfxx.demo10.domain.entity.ProjectClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProjectClassificationRepository extends
        JpaRepository<ProjectClassification,Long>{
}
