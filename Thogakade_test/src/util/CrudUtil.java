package util;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

/*    private static PreparedStatement execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stm.setObject((i + 1), params[i]);
        }
        return stm;
    }

    public static ResultSet executeQuery(String sql, Object... params) throws SQLException, ClassNotFoundException {
        return execute(sql, params).executeQuery();
    }

    public static boolean executeUpdate(String sql, Object... params) throws SQLException, ClassNotFoundException {
        return execute(sql, params).executeUpdate() > 0;
    }*/

    public static <T> T execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stm.setObject((i + 1), params[i]);
        }if (sql.startsWith("SELECT")){
            return (T) stm.executeQuery();
        }else {
            return ((T) (Boolean)(stm.executeUpdate()>0));
        }

    }
}
