package com.stefanini.app.controller;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.service.AssetService;
import com.stefanini.app.service.EmailService;
import com.stefanini.app.utils.AssetType;
import com.stefanini.app.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/equipamentos")
public class AssetController{

    @Autowired
    private AssetService assetService;

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "/add")
    public ResponseEntity addAsset(@RequestParam String heritage, @RequestParam String to, @RequestParam String employee, @RequestParam AssetType type){
        return assetService.saveAsset(new Asset(heritage, type, employee, to));
    }

    @GetMapping(path = "/list")
    public ResponseEntity listAssets(){
        return assetService.getAssets();
    }

    @PatchMapping(path = "/update")
    public ResponseEntity updateAsset(@RequestParam Status status, @RequestParam String scheduledDate, @RequestParam UUID uuid){
        return assetService.updateAsset(status, scheduledDate, uuid);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteAsset(@PathVariable UUID id){
        return assetService.deleteAsset(id);
    }

}