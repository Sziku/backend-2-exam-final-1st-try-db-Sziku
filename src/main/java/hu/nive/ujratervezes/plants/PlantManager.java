package hu.nive.ujratervezes.plants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantManager {

    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public PlantManager(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> getPlantsToBeWatered() {
        final String sql = "select plant_name from plants p join watered w on p.plant_id = w.plant_id where w.watered_at < '2022-01-01' order by p.plant_name;";

        try(Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)){
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            List<String> resultList = new ArrayList<>();
            while (rs.next()){
                resultList.add(rs.getString(1));
            }

            return resultList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
