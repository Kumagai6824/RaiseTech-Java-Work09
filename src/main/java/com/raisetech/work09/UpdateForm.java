package com.raisetech.work09;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateForm {
    private String name;

    public UpdateForm() {
    }

    public UpdateForm(String name) {
        this.name = name;
    }

    public Name convertToNameEntity() {
        Name name = new Name();
        name.setName(this.name);
        return name;
    }
}
