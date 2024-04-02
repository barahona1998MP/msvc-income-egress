package com.msvc.ingresos_egresos.service;

import com.msvc.ingresos_egresos.documents.Detail;

import com.msvc.ingresos_egresos.dto.DetailDto;
import com.msvc.ingresos_egresos.repository.DetailRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {

    final DetailRepository detailRepository;

    public DetailServiceImpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public Detail saveDetail(DetailDto detailDto) {
        Detail detailCreate = Detail.builder()
                .activity(detailDto.getActivity())
                .type(detailDto.getType())
                .value(detailDto.getValue())
                .date(LocalDate.now())
                .activityUrl(detailDto.getActivityUrl())
                .userId(detailDto.getUserId())
                .build();
        return detailRepository.save(detailCreate);
    }


    @Override
    public List<DetailDto> findDetailsBy() {
        return detailRepository.findDetailsBy();
    }

    @Override
    public List<DetailDto> findByUserId(int userId) {
        return detailRepository.findByUserId(userId);
    }

    @Override
    public List<DetailDto> findByDate(LocalDate date) {
        return detailRepository.findByDate(date);
    }


    /**
    private DetailDto convertToDto(Detail detail) {
        DetailDto detailDto = new DetailDto();
        // Aquí debes mapear los atributos de Detail a DetailDto
        detailDto.setActividad(detail.getActividad());
        detailDto.setTipo(detail.getTipo());
        detailDto.setFecha(detail.getFecha());
        detailDto.setValor(detail.getValor());
        detailDto.setUrlActividad(detail.getUrlActividad());
        detailDto.setUserId(detail.getUserId());
        return detailDto;
    }
     **/
}
