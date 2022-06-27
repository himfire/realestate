package com.wrfxx.demo10.controller;

import com.wrfxx.demo10.domain.entity.*;
import com.wrfxx.demo10.domain.service.ReservationService;
import com.wrfxx.demo10.domain.service.UnitClassificationService;
import com.wrfxx.demo10.domain.service.UnitService;
import com.wrfxx.demo10.domain.service.UnitTypeService;
import com.wrfxx.demo10.domain.value.ReservationDTO;
import com.wrfxx.demo10.domain.value.dto.CreateUnitDTO;
import com.wrfxx.demo10.domain.value.dto.ReservationRequestDTO;
import com.wrfxx.demo10.domain.value.dto.UnitDTO;
import com.wrfxx.demo10.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/unit")
@Slf4j
public class UnitController {

    private final UnitService unitService;
    private final UnitTypeService unitTypeService;
    private final UnitClassificationService unitClassificationService;
    private final ReservationService reservationService;
    public UnitController(UnitService unitService, UnitTypeService unitTypeService, UnitClassificationService unitClassificationService, ReservationService reservationService) {
        this.unitService = unitService;
        this.unitTypeService = unitTypeService;
        this.unitClassificationService = unitClassificationService;
        this.reservationService = reservationService;
    }

    @GetMapping("/res/byProjectIdAndUnitNo")
    public ResponseEntity<List<Reservation>> getReservation(
            @RequestParam String unitNo,
            @RequestParam Long projectId,
            Pageable pageable
    ){

        Page<Reservation> page = reservationService.getReservationsByUnitNoAndProjectId(unitNo, projectId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/{slugTitle}/{unitNo}")
    public ResponseEntity<UnitDTO> getUnitByProjectSlugTitleAndUnitNo(@PathVariable String slugTitle,
                                                                      @PathVariable String unitNo){
        return new ResponseEntity<UnitDTO>(unitService.getUnitBySlugTitleAndUnitNo(slugTitle,unitNo), HttpStatus.OK);
    }

    @GetMapping("/{slugTitle}")
    public ResponseEntity getUnitsByProjectSlugTitle(@PathVariable String slugTitle,Pageable pageable){
        return new ResponseEntity(unitService.getUnitsByProjectSlugTitle(slugTitle,pageable), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UnitDTO> updateUnitByProjectSlugTitleAndUnitNo(@PathVariable Long id,
                                                                         @RequestBody UnitDTO dto){

        return new ResponseEntity<UnitDTO>(unitService.updateUnit(dto,id), HttpStatus.OK);
    }
    @GetMapping("/{id}/id")
    public ResponseEntity getUnitByProjectSlugTitle(@PathVariable Long id,Pageable pageable){
        return new ResponseEntity(unitService.getUnitsByProjectId(id,pageable), HttpStatus.OK);
    }

    @PostMapping("/{slugTitle}")
    public ResponseEntity<UnitDTO> createUnitByProjectSlugTitle(
            @PathVariable String slugTitle,
            @RequestBody CreateUnitDTO dto){
        UnitDTO result = unitService.createUnit(dto,slugTitle);
        return new ResponseEntity<UnitDTO>(result,HttpStatus.CREATED);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<UnitDTO> updateProject(@RequestBody UnitDTO dto,@PathVariable Long id){
        UnitDTO result = unitService.updateUnit(dto,id);
        return ResponseEntity.ok(result);
    }*/
    @GetMapping("/type/all")
    public ResponseEntity<List<UnitType>> getAllUnitsType(){
        return new ResponseEntity<>(unitTypeService.getAllUnitTypes(),HttpStatus.OK);
    }


    @GetMapping("/classification/all")
    public ResponseEntity<List<UnitClassification>> getAllProjectsClassification(){
        return new ResponseEntity<>(
                unitClassificationService.getAllUnitClassifications(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAllProjects(@PathVariable Long id){
        unitService.deleteUnit(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/res/{id}")
    public ReservationRequestDTO getReservation(@PathVariable("id") Long id){
        return reservationService.getReservation(id);
    }

    @PostMapping("/res/create")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationRequestDTO reservation){
        reservationService.createReservation(reservation);
        return new ResponseEntity<ReservationDTO>(HttpStatus.CREATED);
    }

    @DeleteMapping("/res/{id}")
    public void deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteReservation(id);
    }

}
