package com.stefanini.app.service;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.repository.AssetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

@Service
public class AssetService {

    private AssetRepository repository;

    public AssetService(AssetRepository assetRepository){
        repository = assetRepository;
    }
    public ResponseEntity saveAsset(Asset asset){
        try{
            repository.save(asset);
            return ResponseEntity.status(201).body(asset);
        }catch (DataIntegrityViolationException err){
            return ResponseEntity.badRequest().body("Error: " + err.getMessage());
        }
    }
}
