package com.stefanini.app.controller;

import com.stefanini.app.dto.AssetCreateDTO;
import com.stefanini.app.dto.AssetUpdateDTO;
import com.stefanini.app.service.AssetService;
import com.stefanini.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/equipamentos")
public class AssetController{

    @Autowired
    private AssetService assetService;

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "/add")
    public ResponseEntity addAsset(@RequestBody AssetCreateDTO assetDTO){
        return assetService.saveAsset(assetDTO);
    }
    @GetMapping(path = "/list")
    public ResponseEntity listAssets(){
        return assetService.getAssets();
    }

    @PatchMapping(path = "/update/{id}")
    public ResponseEntity updateAsset(@PathVariable UUID id, @RequestBody AssetUpdateDTO assetDTO){
        return assetService.updateAsset(assetDTO ,id);}

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteAsset(@PathVariable UUID id){
        return assetService.deleteAsset(id);}

}