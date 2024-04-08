package com.msvc.ingresos_egresos.controller;

import com.msvc.ingresos_egresos.documents.Detail;
import com.msvc.ingresos_egresos.dto.DetailDto;
import com.msvc.ingresos_egresos.service.DetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detail")
public class DetailController {

    final DetailService detailService;


    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @PostMapping("/create")
    public ResponseEntity<Detail> createDetail(@RequestBody DetailDto detailDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detailService.saveDetail(detailDto));
    }

    /**
    @GetMapping("/getDetails")
    public ResponseEntity<List<DetailDto>> getAllDetalle() {
        List<DetailDto> details = detalleService.findAllDetails();
        if (details.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(details);
    }
     **/

    @GetMapping("/all")
    public ResponseEntity<List<DetailDto>> findDetailsBy() {
        System.out.println("Hola");
        List<DetailDto> getDetails = detailService.findDetailsBy();
        if (getDetails.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(getDetails);

    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<List<DetailDto>> findCarByUserId(@PathVariable int userId) {
        List<DetailDto> details = detailService.findByUserId(userId);
        return ResponseEntity.ok(details);
    }


    public List<DetailDto> getDetailsByDate(@PathVariable String date) {
        try{
            // Convertir la cadena de fecha a LocalDate
            LocalDate searchDate = LocalDate.parse(date);

            // Obtener la lista de detalles filtrada por fecha
            return detailService.findByDate(searchDate);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    @GetMapping("/details/filterByDate")
    public ResponseEntity<List<DetailDto>> filterDetailsByDate(@RequestParam("date") LocalDate date) {
        try{
            List<DetailDto> detailDtoList = detailService.findByDate(date);
            if (detailDtoList.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(detailDtoList);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    //Get activity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DetailDto>> getActivityById(@PathVariable String id) {
        Optional<DetailDto> activityDto = detailService.findDetailDtoById(id);
        return ResponseEntity.ok(activityDto);
    }

    //Update URL of an activity
    @PutMapping("/updateUrl/{id}")
    public ResponseEntity<Detail> updateActivityUrl(
            @PathVariable String id,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        // Get the activity by its ID
        Optional<DetailDto> optionalDetailDto = detailService.findDetailDtoById(id);

        if (optionalDetailDto.isPresent()) {
            DetailDto detailDto = optionalDetailDto.get();

            // Save the image and get the URL if a file is provided
            String imageUrl = null;
            if (file != null) {
                imageUrl = detailService.saveImage(file);
                detailDto.setActivityUrl(imageUrl);
            }

            // Save the changes in the database
            Detail updatedDetail = detailService.saveDetail(detailDto);
            return ResponseEntity.ok(updatedDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Get the total income and expenses
    @GetMapping("/total/{userId}")
    public ResponseEntity<Double> calculateUserActivity(@PathVariable int userId) {
        List<DetailDto> details = detailService.findByUserId(userId);

        double totalValue = 0.0;
        for(DetailDto detail : details){
            if (detail.getType().equals("1")) {
                totalValue += detail.getValue();
            } else if (detail.getType().equals("2")) {
                totalValue -= detail.getValue();
            }

        }

        return ResponseEntity.ok(totalValue);
    }

    //Get the total income
    @GetMapping("/income/{userId}")
    public ResponseEntity<Double> incomeUserActivity(@PathVariable int userId) {
        List<DetailDto> details = detailService.findByUserId(userId);

        double totalValue = 0.0;
        for(DetailDto detail : details){
            if(detail.getType().equals("1")){
                totalValue += detail.getValue();
            }


        }

        return ResponseEntity.ok(totalValue);
    }

    //Get the total expenses
    @GetMapping("/expense/{userId}")
    public ResponseEntity<Double> expenseUserActivity(@PathVariable int userId) {
        List<DetailDto> details = detailService.findByUserId(userId);

        double totalValue = 0.0;
        for(DetailDto detail : details){

            if(detail.getType().equals("2")){
                totalValue += detail.getValue();
            }


        }

        return ResponseEntity.ok(totalValue);
    }




}
