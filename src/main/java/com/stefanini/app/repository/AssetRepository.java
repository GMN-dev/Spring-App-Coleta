package com.stefanini.app.repository;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.utils.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AssetRepository extends CrudRepository<Asset, UUID> {
    List<Asset> findByStatusIn(List<Status> status);
}