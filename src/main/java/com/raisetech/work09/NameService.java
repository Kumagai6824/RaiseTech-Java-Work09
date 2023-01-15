package com.raisetech.work09;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NameService {
    List<Name> findAll();
}
