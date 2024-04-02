package com.msvc.ingresos_egresos.controller;

import com.msvc.ingresos_egresos.documents.Detail;
import com.msvc.ingresos_egresos.dto.DetailDto;
import com.msvc.ingresos_egresos.service.DetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DetailController {

    final DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @PostMapping("/detail")
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

    @GetMapping("/details")
    public ResponseEntity<List<DetailDto>> findDetailsBy() {
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
        // Convertir la cadena de fecha a LocalDate
        LocalDate searchDate = LocalDate.parse(date);

        // Obtener la lista de detalles filtrada por fecha
        return detailService.findByDate(searchDate);
    }

    @GetMapping("/details/filterByDate")
    public ResponseEntity<List<DetailDto>> filterDetailsByDate(@RequestParam("date") LocalDate date) {
        List<DetailDto> detailDtoList = detailService.findByDate(date);
        if (detailDtoList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(detailDtoList);
    }

    /*
    * filtrar por fecha
    * aplicar jwt api gateway
    * */
}
