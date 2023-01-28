package com.raisetech.work09;

import java.util.List;

public interface NameService {
    List<Name> findAll();

    Name findById(int id) throws Exception;

    void createName(CreateForm form);

    void deleteById(int id);
}
