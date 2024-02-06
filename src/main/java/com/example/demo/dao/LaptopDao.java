package com.example.demo.dao;

import com.example.demo.model.Laptop;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class LaptopDao {
    private String URL = "jdbc:postgresql://localhost:5433/homework";
    private String USER = "postgres";
    private String PASSWORD = "postgres";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String insert (Laptop model){
        String SQL = "insert into laptop (id, brand, model, price, photo) values (?,?,?,?,?)";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL))
        {
            pstmt.setLong(1,model.getId());
            pstmt.setString(2,model.getBrand());
            pstmt.setString(3,model.getModel());
            pstmt.setBigDecimal(4,model.getPrice());
            pstmt.setBytes(5, model.getPhoto());
            return "Successful insert!";
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "insert failed!";
    }

    public List<Laptop> getAll (){
        List<Laptop> laptops = new ArrayList<>();
        String SQL = "select * from laptop";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery())
        {
            while (rs.next()){
                Laptop laptop = new Laptop();

                laptop.setId(rs.getLong("id"));
                laptop.setBrand(rs.getString("brand"));
                laptop.setModel(rs.getString("model"));
                laptop.setPrice(rs.getBigDecimal("price"));
                laptop.setPhoto(rs.getBytes("photo"));

                laptops.add(laptop);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return laptops;
    }

    public Laptop getById (Long id){
        Laptop laptop = new Laptop();
        String SQL = "select * from laptop where id = ?";
        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery())
        {
            pstmt.setLong(1, id);
           while (rs.next()){
               laptop.setId(rs.getLong("id"));
               laptop.setBrand(rs.getString("brand"));
               laptop.setModel(rs.getString("model"));
               laptop.setPrice(rs.getBigDecimal("price"));
               laptop.setPhoto(rs.getBytes("photo"));
           }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return laptop;
    }

    public String update (Long id, Laptop model){
        String SQL = "update laptop l set brand = ?, model = ?, price = ?, photo = ? where l.id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL))
        {
            pstmt.setLong(5, id);
            pstmt.setString(1, model.getBrand());
            pstmt.setString(2, model.getModel());
            pstmt.setBigDecimal(3, model.getPrice());
            pstmt.setBytes(4, model.getPhoto());
            return "Successful update!";
        } catch (SQLException e){
            e.getMessage();
        }
        return "Update failed!";
    }

    public String delete(Long id){
        String SQL = "delete from laptop l where l.id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL))
        {
            pstmt.setLong(1,id);
            pstmt.executeUpdate();
            return "Deleted!";
        } catch (SQLException e){
            e.getMessage();
        }
        return "Delete failed!";
    }
}


