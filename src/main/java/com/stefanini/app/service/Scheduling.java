package com.stefanini.app.service;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.repository.AssetRepository;
import com.stefanini.app.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Scheduling {

    @Autowired
    public AssetRepository repository;

    @Scheduled(cron = "0 0 18 * * MON-FRI")
    public void updatePendingDays() {
        List<Asset> pendingAssets = this.repository.findByStatusIn(List.of(Status.PENDENTE, Status.AGENDADO));
        pendingAssets.forEach(asset -> asset.setPendingDays(asset.getPendingDays() + 1));
        repository.saveAll(pendingAssets);
    }
}
