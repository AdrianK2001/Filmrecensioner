package com.example.Filmrecensioner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private Long id;
    private Long title;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
