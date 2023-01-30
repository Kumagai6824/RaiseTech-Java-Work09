package com.raisetech.work09;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateForm {
    private String name;

    public CreateForm() {

    }

    public CreateForm(String name) {
        this.name = name;
    }


}
