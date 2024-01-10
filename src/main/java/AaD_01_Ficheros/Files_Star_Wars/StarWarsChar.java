package AaD_01_Ficheros.Files_Star_Wars;

import java.util.Objects;

public class StarWarsChar
{
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StarWarsChar that = (StarWarsChar) o;

        return Objects.equals(name.trim(), that.name.trim());
    }

}
