package com.msvc.ingresos_egresos.service;

import com.msvc.ingresos_egresos.documents.Detail;

import com.msvc.ingresos_egresos.dto.DetailDto;
import com.msvc.ingresos_egresos.repository.DetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements DetailService {

    final DetailRepository detailRepository;

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

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
    public Optional<DetailDto> findDetailDtoById(String id) {
        return detailRepository.findDetailDtoById(id);
    }


    @Override
    public List<DetailDto> findByUserId(int userId) {
        return detailRepository.findByUserId(userId);
    }




    @Override
    public List<DetailDto> findByDate(LocalDate date) {
        return detailRepository.findByDate(date);
    }



    public String saveImage(MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.write(path, file.getBytes());
                return "/uploads/" + fileName;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
