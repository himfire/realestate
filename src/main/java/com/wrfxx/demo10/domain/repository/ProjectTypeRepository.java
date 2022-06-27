package com.wrfxx.demo10.domain.repository;

import com.wrfxx.demo10.domain.entity.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProjectTypeRepository extends JpaRepository<ProjectType,Long>{
}
