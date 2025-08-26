package com.Enlace.io.GPStracker.repository;

 import com.Enlace.io.GPStracker.entity.ShareLinkPublic;
 import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShareLinkPublicRepo extends JpaRepository<ShareLinkPublic, UUID> {
    Optional<ShareLinkPublic> findByTokenAndActiveTrue(String token);
}
