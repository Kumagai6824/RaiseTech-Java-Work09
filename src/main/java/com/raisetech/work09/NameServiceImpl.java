package com.raisetech.work09;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NameServiceImpl implements NameService {
    private final NameMapper nameMapper;

    public NameServiceImpl(NameMapper nameMapper) {
        this.nameMapper = nameMapper;
    }

    @Override
    public List<Name> findAll() {
        return nameMapper.findAll();
    }

    @Override
    public Name findById(int id) throws Exception {
        Name nameWithId = nameMapper.findById(id).orElseThrow(() -> {
            return new NotFoundException("Name not found with id" + id);
        });
        return nameWithId;
    }

    @Override
    public void createName(CreateForm form) {
        nameMapper.createName(form);
    }
}
