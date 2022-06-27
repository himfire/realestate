package com.wrfxx.demo10.domain.repository;

import com.wrfxx.demo10.domain.entity.Unit;
import com.wrfxx.demo10.domain.value.dto.UnitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UnitRepository extends JpaRepository<Unit,Long> {
    Unit findByUnitNo(String unitNo);

    @Query(value = "select u from Unit u where u.unitNo = ?2 and u.project.slugTitle = ?1")
    Optional<Unit> findByProjectSlugTitleAndUnitNo(String SlugTitle, String unitNo);

    /*@Query("select u from Unit u where u.noOfFloors = ?1")
    Page<UnitDTO> findByNoOfFloors(Long noOfFloors, Pageable pageable);

    @Query("select u from Unit u where u.noOfBedrooms = ?1")
    UnitDTO findByNoOfBedrooms(Long noOfBedrooms, Pageable pageable);

    @Query("select u from Unit u where u.noOfBathrooms = ?1")
    UnitDTO findByNoOfBathrooms(Long noOfBathrooms, Pageable pageable);*/

    Page<Unit> findByProjectId(Long projectId, Pageable pageable);
}
