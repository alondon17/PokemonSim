package Project;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String SQL = "SELECT * FROM pokemon.species " +
                "ORDER BY id ASC";
ArrayList<Species> list=new ArrayList<>();


            PreparedStatement pstmt = conn.prepareStatement(SQL);


            conn.setSchema("liat");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if(rs.getInt("id")>1)
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
}
