package com.test.studycafe.repository;

import com.test.studycafe.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone,Long> {
    Zone findByCityAndProvince(String city, String province);
}
