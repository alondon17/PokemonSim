package Project.SystemStuff;

import Project.Battles.PokemonData.Stats.BaseStats;
import Project.Battles.PokemonData.Species;
import Project.SystemStuff.Utilities.Methoder;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

public class DBManager {
    static Connection conn;

    static {
        try {
            conn = connect();
        } catch (SQLException throwables) {
            System.out.println("No DB found");
        }
    }

    public static void main(String[] args) {


    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "Aa123456");
    }

    public static ArrayList<Species> getSpecies() throws SQLException {
        String SQL = "SELECT * FROM public.pokemon " +
                "ORDER BY id ASC";
        ArrayList<Species> list = new ArrayList<>();

        conn.setSchema("public");
        PreparedStatement pstmt = conn.prepareStatement(SQL);


//            conn.setSchema("liat");

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            if (rs.getInt("id") >= 1)
                list.add(new Species(rs.getInt("id"),
                        rs.getString("specname"),
                        new BaseStats(Methoder.toIntArr(rs.getArray("baseStats"))),
                        rs.getInt("expYield"),
                        Methoder.toIntArr(rs.getArray("ivYield")),
                        rs.getInt("type1"),
                        rs.getInt("type2"),
                        Methoder.toIntArr(rs.getArray("learnset"))));
        }

        return list;
    }

    public static void pushPokemonToDB() throws SQLException {
        String SQL = "DROP TABLE IF EXISTS public.pokemon;\n" +
                " CREATE TABLE public.pokemon\n" +
                "(\n" +
                "    id integer NOT NULL,\n" +
                "    specname character varying NOT NULL,\n" +
                "    \"baseStats\" integer[],\n" +
                "    \"expYield\" integer,\n" +
                "    \"ivYield\" smallint[],\n" +
                "    type1 smallint NOT NULL,\n" +
                "    type2 smallint NOT NULL,\n" +
                "    \"learnSet\" integer[],\n" +
                "    PRIMARY KEY (id)\n" +
                ");\n" +
                "\n" +
                "ALTER TABLE public.pokemon\n" +
                "    OWNER to postgres;\n" +
                "INSERT INTO public.pokemon\n" +
                "VALUES (1, 'Bulbasaur', '{49, 65, 49, 65, 65, 45}', 64, '{0, 0, 0, 1, 0, 0}', 12, 4, '{1, 45, 1, 33, 3, 22, 6, 74, 9, 73, 12, 75, 15, 77, 15, 79, 18, 402, 21, 36, 24, 230, 27, 235, 30, 388, 33, 38, 36, 76}');";
        conn.setSchema("public");
        PreparedStatement pstmt = conn.prepareStatement(SQL);


//            conn.setSchema("liat");

        pstmt.executeUpdate();
//        String SQL2 = "SELECT * FROM pokemon.species " +
//                "ORDER BY id ASC";
//
//        conn.setSchema("public");
//        pstmt = conn.prepareStatement(SQL);
//
//
////            conn.setSchema("liat");
//
//        ResultSet rs = pstmt.executeQuery();
//        while (rs.next()) {
//            if(rs.getInt("id")>1)
//                list.put(rs.getInt("id"),new Species(rs.getInt("id"),
//                        rs.getString("specname"),
//                        new BaseStats(Methoder.toIntArr(rs.getArray("baseStats"))),
//                        rs.getInt("expYield"),
//                        Methoder.toIntArr(rs.getArray("ivYield")),
//                        rs.getInt("type1"),
//                        rs.getInt("type2"),
//                        Methoder.toIntArr(rs.getArray("learnset"))));
//        }
    }

    public static boolean doesTableExist(String schema, String name) {
        try {
            ResultSet rs = conn.getMetaData().getTables(null, schema, name, null);
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(name)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException throwables) {
            return false;
        }
    }
}
