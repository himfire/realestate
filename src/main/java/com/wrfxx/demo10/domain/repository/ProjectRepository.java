package com.wrfxx.demo10.domain.repository;

import com.wrfxx.demo10.domain.entity.Project;
import com.wrfxx.demo10.domain.value.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long>{

    boolean existsBySlugTitle(String slugTitle);
    Optional<Project> findBySlugTitle(String slugTitle);
    List<Project> findByUser(String user);

    @Query("select p from Project p " +
            "where ?1 is null or p.title_en like %?1% " +
            "or p.title_ar like %?1%  ")
    Page<Project> search(String searchText, Pageable pageable);
}
