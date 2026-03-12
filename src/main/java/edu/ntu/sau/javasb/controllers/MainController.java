package edu.ntu.sau.javasb.controllers;


import edu.ntu.sau.javasb.service.ExcellModifier;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainController {
    @Autowired
    ExcellModifier excellModifier;

    @GetMapping("/")
    String getWelcome(Model model) {

        return "index";
    }

    @PostMapping("/process")

    public ResponseEntity<byte[]> processExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }

        byte[] result = excellModifier.modifyExcel(file.getInputStream());
        String filename = "result.xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(result);
    }


}
