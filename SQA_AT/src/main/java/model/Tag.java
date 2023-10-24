package model;

public class Tag {
    private String id;
    private String name;

    public Tag() {
    }


    public Tag(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "category{ " + "Id" + id +
                ",Name = '" + name + '}';
    }
}
