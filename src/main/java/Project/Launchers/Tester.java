package Project.Launchers;

import Project.Battles.PokemonData.SpeciesList;

import java.sql.SQLException;

public class Tester {
    public static void main(String[] args) throws SQLException {
        SpeciesList.DBtoInitializerCode();
    }

}
