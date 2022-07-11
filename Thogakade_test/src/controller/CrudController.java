package controller;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CrudController {
    public static ArrayList<String> getCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM Customer");
        ArrayList<String> arrayList=new ArrayList<>();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

public static ArrayList<String> getItemCode() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.execute("SELECT code FROM Item");
        ArrayList<String> arrayList=new ArrayList<>();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
}
}
