package com.example.springbackend.Model;


import javax.persistence.*;

@Entity
@Table(name = "value")
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value1")
    private String value1;

    @Column(name = "value2")
    private String value2;

    @Column(name = "value3")
    private Float value3;

    public Value() {
    }

    public Value(String value1, String value2, Float value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public Float getValue3() {
        return value3;
    }

    public void setValue3(Float value3) {
        this.value3 = value3;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                '}';
    }
}