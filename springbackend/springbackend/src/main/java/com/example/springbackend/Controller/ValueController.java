package com.example.springbackend.Controller;

import com.example.springbackend.Model.Value;
import com.example.springbackend.Repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ValueController {
    @Autowired
    ValueRepository valueRepository;

    @GetMapping("/values")
//    Sửa string title theo giá tị cần tìm kiếm
    public ResponseEntity<List<Value>> getAllValues(@RequestParam(required = false) String value1) {
        try {
            List<Value> values = new ArrayList<>();

            if (value1 == null)
                values.addAll(valueRepository.findAll());
            else
//                sửa theo findby... ở bên repository
                values.addAll(valueRepository.findByValue1Containing(value1));

            if (values.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(values, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/values/{id}")
    public ResponseEntity<Value> getValueById(@PathVariable("id") long id) {
        Optional<Value> valueData = valueRepository.findById(id);
        if (valueData.isPresent()) {
            return new ResponseEntity<>(valueData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/values")
    public ResponseEntity<Value> createValue(@RequestBody Value value) {
        try {
//            sửa get theo table
            Value _value = valueRepository
                    .save(new Value(
                            value.getValue1(),
                            value.getValue2(),
                            value.getValue3()));
            return new ResponseEntity<>(_value, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/values/{id}")
    public ResponseEntity<Value> updateValue(@PathVariable("id") long id, @RequestBody Value value) {
        Optional<Value> valueData = valueRepository.findById(id);

        if (valueData.isPresent()) {
            Value _value = valueData.get();
//            sửa get det theo table
            _value.setValue1(value.getValue1());
            _value.setValue2(value.getValue2());
            _value.setValue3(value.getValue3());

//            ---------------------------------------
            return new ResponseEntity<>(valueRepository.save(_value), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/values/{id}")
    public ResponseEntity<HttpStatus> deleteValue(@PathVariable("id") long id) {
        try {
            valueRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/values")
    public ResponseEntity<HttpStatus> deleteAllValues() {
        try {
            valueRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
