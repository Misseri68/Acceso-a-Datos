package AaD_04_ManejoConectores_SQL.PokemonDAO;

import java.util.Objects;

public class Pokemon {
    private int idNum;
    private String name;
    private int HP;
    private int speed;
    private int attack;
    private Integer specialAttack; // Puede ser nulo
    private int defense;
    private Integer specialDefense; // Puede ser nulo
    private Integer evolveId; // Puede ser nulo
    private int generation;

    // Constructor
    public Pokemon(int idNum, String name, int HP, int speed, int attack, int specialAttack, int defense,
                   int specialDefense, int evolveId, int generation) {
        this.idNum = idNum;
        this.name = name;
        this.HP = HP;
        this.speed = speed;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
        this.evolveId = evolveId;
        this.generation = generation;
    }

    // Getters y setters (métodos para acceder y modificar los atributos)

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Integer getEvolveId() {
        return evolveId;
    }

    public void setEvolveId(Integer evolveId) {
        this.evolveId = evolveId;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "idNum=" + idNum +
                ", name='" + name + '\'' +
                ", HP=" + HP +
                ", speed=" + speed +
                ", attack=" + attack +
                ", specialAttack=" + specialAttack +
                ", defense=" + defense +
                ", specialDefense=" + specialDefense +
                ", evolveId=" + evolveId +
                ", generation=" + generation +
                '}';
    }

}

