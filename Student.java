
package com.example.demo;

import java.util.List;

public class Student {
    private Long id;
    private String name;
    private Double score;
    private List<String> skills;

    // Default Constructor
    public Student() {}

    // Parameterized Constructor
    public Student(Long id, String name, Double score, List<String> skills) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.skills = skills;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
}