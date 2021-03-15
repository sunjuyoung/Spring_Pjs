package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Zone;
import com.test.studycafe.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;

    @PostConstruct
    public void initZoneData() throws IOException {
        if(zoneRepository.count()==0){
            Resource resource = new ClassPathResource("zone_city.csv");
           List<Zone> zoneList= Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream()
                                .map(line->{
                                    String[] split = line.split(",");
                                    return Zone.builder()
                                            .city(split[0])
                                            .localName(split[1])
                                            .province(split[2]).build();
                                }).collect(Collectors.toList());

           zoneRepository.saveAll(zoneList);
        }
    }



    public List<String> zoneList() {
        return zoneRepository.findAll().stream().map(a->a.toString()).collect(Collectors.toList());
    }
}
