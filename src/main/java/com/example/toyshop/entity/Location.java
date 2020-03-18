package com.example.toyshop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "LOCATION_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "LOCATION_GENERATOR", allocationSize = 1, sequenceName = "location_id_seq")
    private long id;

    @Column(name = "location_name")
    private String locationName;

    public Location() {
    }

    public Location(long id, String locationName) {
        this.id = id;
        this.locationName = locationName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        return Objects.equals(id, other.getId());
    }
}
