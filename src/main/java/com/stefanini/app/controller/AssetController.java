package com.stefanini.app.controller;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipamentos")
public class AssetController{

    @Autowired
    private AssetService assetService;

    @PostMapping(path = "/add")
    public ResponseEntity addAsset(@RequestParam String heritage, @RequestParam String to, @RequestParam String subject, @RequestParam String text ){
        return assetService.saveAsset(new Asset(heritage), to, subject, text);
    }

    @GetMapping(path = "/listagem")
    public ResponseEntity listAssets(){
        return assetService.getAssets();
    }
}