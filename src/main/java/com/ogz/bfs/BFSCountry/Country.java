package com.ogz.bfs.BFSCountry;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Country {
    @SerializedName("cca3")
    private String name;
    private List<String> borders;
}
