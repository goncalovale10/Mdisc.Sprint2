package pt.ipp.isep.dei.esoft.project.domain.mdisc.MDISC.src;

public class Vertex {

    private String name;

    private static final String NAME_POR_OMISSAO = "no name";

    public Vertex(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return String.format("%s: ", name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Vertex otherVertex = (Vertex) other;
        return name.equalsIgnoreCase(otherVertex.name);
    }
}
