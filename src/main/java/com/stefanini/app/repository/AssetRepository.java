package com.stefanini.app.repository;

import com.stefanini.app.entity.Asset;
import com.stefanini.app.utils.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssetRepository extends CrudRepository<Asset, Long> {
    List<Asset> findByStatusIn(List<Status> status);
}
