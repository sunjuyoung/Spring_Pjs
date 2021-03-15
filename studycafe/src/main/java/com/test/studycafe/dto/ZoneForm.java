package com.test.studycafe.dto;

import com.test.studycafe.domain.Zone;
import lombok.Data;

@Data
public class ZoneForm {


    private String zones;

    public String getCity(){
        return zones.substring(0,zones.indexOf("("));
    }
    public String getLocalname(){
        return zones.substring(zones.indexOf("(")+1,zones.indexOf(")"));
    }

    public String getProvince(){
        return zones.substring(zones.indexOf(")")+2);
    }
    public Zone getZone(){
        return  Zone.builder().city(this.getCity()).localName(this.getLocalname()).province(this.getProvince()).build();
    }

}
