package com.shdata.health.gzss.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Doctor implements Serializable {

    private String gh;

    private String xm;
}
