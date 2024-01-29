package br.com.ada.pooii.projeto_final.adatask.domain;

public class User {
    private static int nextId = 1;
    private Integer id;
    private String name;

    public User(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public Integer getId() { return id; }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String parse(String user){
        return getName();
    }

    @Override
    public String toString() {
        return name;
    }
}
