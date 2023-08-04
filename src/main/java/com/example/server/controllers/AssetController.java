package com.example.server.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private String assetId;

    @PostMapping("/upload")
    public AssetResponse uploadAsset(@RequestParam("file") MultipartFile file) {
        // Lógica para almacenar el archivo PDF recibido y obtener el identificador único (assetId)

        // Crear la respuesta JSON con la información del activo almacenado
        AssetResponse response = new AssetResponse(assetId, file.getOriginalFilename(), "2023-08-01");
        return response;
    }
}

class AssetResponse {
    private String assetId;
    private String fileName;
    private String uploadDate;

    public AssetResponse(String assetId, String fileName, String uploadDate) {
        this.assetId = assetId;
        this.fileName = fileName;
        this.uploadDate = uploadDate;
    }

    // getters y setters
}
