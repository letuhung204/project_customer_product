package com.rfx.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rfx.api.model.TableRefreshToken;


public interface RefreshTokenRepository  extends JpaRepository<TableRefreshToken, Integer> {
}
