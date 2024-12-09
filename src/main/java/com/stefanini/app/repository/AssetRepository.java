package com.stefanini.app.repository;

import com.stefanini.app.entity.Asset;
import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends CrudRepository<Asset, Long> {
}
