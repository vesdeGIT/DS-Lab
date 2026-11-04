package gr.hua.dit.kvdb;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
public class Homepage {

    private ConcurrentHashMap<String, String> database;

    public Homepage() {
        database = new HashMap<>();
    }

    @GetMapping("/")
    public String index() {
        return "Home Page";
    }


    @PostMapping("/kv/{key}")
    public void insert(@PathVariable String key, String value) {
        if(database.containsKey(key)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Key already exists");
            // throw new IllegalArgumentException("Key already exists");
        }
        this.database.put(key, value);
    }

    @DeleteMapping("/kv/{key}")
    public String delete(@PathVariable String key) {
        String oldValue = this.database.remove(key);
        return oldValue == null ? "<null>" : oldValue;

    }

    @PatchMapping("/kv/{key}")
    public String update(@PathVariable String key, String value) {
        if(!this.database.containsKey(key)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Key does not exist");
        }
        String oldValue = this.database.put(key, value);
        return oldValue == null ? "<null>" : oldValue;

    }

    @PutMapping("/kv/{key}")
    public String upsert(@PathVariable String key, String value) {

        String oldValue = this.database.put(key, value);
        return oldValue == null ? "<null>" : oldValue;
    }

    @GetMapping("/kv/{key}")
    public String get(@PathVariable String key) {
        String value = this.database.get(key);
        return value;
    }

    @GetMapping(value = "/kv")
    public String list(){
        Set<String> keySet = this.database.keySet();
        String keys = String.join("\n", keySet);
        return keys;
    }

    @PostConstruct
    public void init() {
        this.database.put("name", "key-value database");
        this.database.put("programmer", "Χρη");
        this.database.put("version", "0.1.0");

    }

}
