package com.stefanini.app.controller;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipamentos")
public class AssetController{

    @Autowired
    private AssetService assetService;

    @PostMapping(path = "/add")
    public String addAsset(@RequestParam String heritage){
        return assetService.saveAsset(new Asset(heritage));
    }
}
