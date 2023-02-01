package com.raisetech.work09;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class NameController {

    private final NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/names")
    public List<Name> getNames() {
        return nameService.findAll();
    }

    @GetMapping("/names/{id}")
    public Name findById(
            @PathVariable(value = "id")
            int id) throws Exception {
        return nameService.findById(id);

    }

    @PostMapping("/names")
    public ResponseEntity<Map<String, String>> postUser(@RequestBody @Validated CreateForm form, UriComponentsBuilder uriComponentsBuilder) {
        Name entity = form.convertToNameEntity();
        nameService.createName(entity);
        int id = entity.getId();
        String name = entity.getName();
        URI url = uriComponentsBuilder.path("/names/" + id).build().toUri();
        return ResponseEntity.created(url).body(Map.of("message", "name:" + name + " was successfully registered"));
    }

    @DeleteMapping("/names/{id}")
    public Map<String, String> delteById(
            @PathVariable(value = "id")
            int id) {
        nameService.deleteById(id);
        return Map.of("message", "id: " + id + " was successfully deleted");
    }
}
