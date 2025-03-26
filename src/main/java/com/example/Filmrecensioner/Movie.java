package com.example.Filmrecensioner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private Long id;
    private Long title;


    public Long title() {
        return title;
    }
}
