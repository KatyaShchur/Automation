package model;

public class PetBuilder {

    private Integer id;
    private String name;
    private String status;
    private Category category;
    private Tag tag;

    public PetBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public PetBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder setStatus(String status) {
        this.status = status;
        return this;

    }

    public Pet build() {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        pet.setStatus(status);
        return pet;
    }

}
