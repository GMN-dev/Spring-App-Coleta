package com.stefanini.app.service;

import com.stefanini.app.dto.AssetCreateDTO;
import com.stefanini.app.dto.AssetUpdateDTO;
import com.stefanini.app.entity.Asset;
import com.stefanini.app.repository.AssetRepository;
import com.stefanini.app.utils.Formatter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

@Service
public class AssetService {

    private final AssetRepository repository;

    @Autowired
    EmailService emailService;

    public AssetService(AssetRepository assetRepository) {
        repository = assetRepository;
    }

    public ResponseEntity saveAsset(AssetCreateDTO assetDTO) {
        Asset asset = new Asset(assetDTO.heritage(), assetDTO.assetType(), assetDTO.name(), assetDTO.email());
        try {
            if(asset.getHeritage() != null && !asset.getHeritage().isEmpty()) {
                if (asset.getHeritage().length() == 7) {
                    if (asset.getHeritage().startsWith("N00") || asset.getHeritage().startsWith("C0")) {
                        emailService.SendEmail(asset);
                        repository.save(asset);
                        return ResponseEntity.noContent().build();
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


    public ResponseEntity<Void> deleteAsset(UUID uuid) {
        if (!repository.existsById(uuid)) {
            return ResponseEntity.notFound().build();
        }
        try {
            repository.deleteById(uuid);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a exclusão for bem-sucedida
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 em caso de erro no banco
        }
    }


    public ResponseEntity updateAsset(AssetUpdateDTO assetDTO, UUID id){
        Asset asset = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        try{
            asset.setScheduledDate(Formatter.setStringToLocalDate(assetDTO.scheduledDate()));
            asset.setStatus(assetDTO.status());
            repository.save(asset);
            return ResponseEntity.noContent().build();
        }catch (Error err){
            return ResponseEntity.badRequest().body("Erro de conversão de data!");
        }
    }

}
