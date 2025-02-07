package com.stefanini.app.service;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.repository.AssetRepository;
import com.stefanini.app.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository repository;

    @Autowired
    EmailService emailService;

    public AssetService(AssetRepository assetRepository) {
        repository = assetRepository;
    }

    public ResponseEntity saveAsset(Asset asset, String to, String subject, String text) {
        try {
            if(asset.getHeritage() != null && !asset.getHeritage().isEmpty()) {
                if (asset.getHeritage().length() == 7) {
                    if (asset.getHeritage().startsWith("N00") || asset.getHeritage().startsWith("C0")) {
                        emailService.SendEmail(to, subject, text);
                        Asset response = repository.save(asset);
                        return ResponseEntity.status(HttpStatus.CREATED).body(response);
                    } else {
                        return ResponseEntity.badRequest().body("O prefixo padrão de patrimônio não está correto (C0XXXXX ou N00XXXX)");
                    }
                } else {
                    return ResponseEntity.badRequest().body("Erro: O número de caracteres do patrimônio não confere. Esperado 7 caracteres." + asset.getHeritage());
                }
            }else{
                return ResponseEntity.badRequest().body("O valor do patrimônio não pode ser vazio ou nulo");
            }
        } catch (DataIntegrityViolationException err) {
            return ResponseEntity.badRequest().body("Error: " + err.getMessage());
        }
    }

    public ResponseEntity getAssets(){
        Iterable<Asset> listAssets = repository.findAll();
        ResponseEntity response = ResponseEntity.ok(listAssets);
        return response;
    }

//    public void updatePendingDays(Asset asset){
//
//        asset.setPendingDays();
//    }
}
