package com.icia.mjpa.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DEV_TEAM")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TID_SEQ_GENERATOR")
    @SequenceGenerator(name = "TID_SEQ_GENERATOR", sequenceName = "TID_SEQ", allocationSize = 1)
    private int TId;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TPROJECT_SEQ_GENERATOR")
    @SequenceGenerator(name = "TPROJECT_SEQ_GENERATOR", sequenceName = "TPROJECT_SEQ", allocationSize = 1)
    private int TProject;

}