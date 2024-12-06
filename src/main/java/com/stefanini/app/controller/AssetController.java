package com.stefanini.app.controller;

import com.stefanini.app.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipamentos")
public class AssetController{

    @PostMapping(path = "/add")
    public Asset addAsset(@RequestParam String heritage){
        Asset asset = new Asset(heritage);

        return asset;
    }

}
