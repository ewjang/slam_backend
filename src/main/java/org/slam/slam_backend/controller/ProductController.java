package org.slam.slam_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slam.slam_backend.dto.ProductDTO;
import org.slam.slam_backend.util.CustomFileUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.List;
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(("/api/products"))
public class ProductController {

    private final CustomFileUtil fileUtil;


    @PostMapping("/")
    public Map<String, String> register(ProductDTO productDTO) {

        log.info("register: " + productDTO);

        List<MultipartFile> files = productDTO.getFiles();

        List<String> uploadedFileNames = fileUtil.saveFiles(files);

        productDTO.setUplaodFileNames(uploadedFileNames);

        log.info(uploadedFileNames);

        return Map.of("RESULT", "SUCCESS");
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName) {

        return fileUtil.getFile(fileName);
    }
}
