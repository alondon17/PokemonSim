package Project.Launchers;

import Project.Battles.PokemonData.SpeciesList;
import Project.Visuals.ConsoleColors;

import java.sql.SQLException;

public class Tester {
    public static void main(String[] args) throws SQLException {
//        System.out.println("\033[31;1mHello\033[0m, \033[32;1;2mworld!\033[0m");
        System.out.println(ConsoleColors.RED+"Hello, \033[32;1;2mworld!\033[0m");
//        SpeciesList.DBtoInitializerCode();
    }

}
