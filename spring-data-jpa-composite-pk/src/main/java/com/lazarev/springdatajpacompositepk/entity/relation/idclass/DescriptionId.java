package com.lazarev.springdatajpacompositepk.entity.relation.idclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionId implements Serializable {
    private Integer descriptionId;
    private UUID ownerId;
}
