package org.example;

import org.example.Entities.Street;
import org.example.Services.HibernateInterfaceRealization;
import org.example.Services.JDBSInterfaceRealization;
import org.example.Services.MyBatisInterfaseRealization;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeRequetTest {

    @Test
    public void TestByAdding() throws SQLException, IOException {
        List<Street> streetsForTest = new ArrayList<>();
        for(int i = 1; i < 101; ++i){
            streetsForTest.add(new Street(i, "newstreet", 123));
        }
        String url = "jdbc:h2:mem:lab2;INIT=RUNSCRIPT FROM 'classpath:database_preparation.sql';";
        String user = "";
        String password = "";
        JDBSInterfaceRealization jdbsInterfaceRealization = new JDBSInterfaceRealization(url, user, password);
        HibernateInterfaceRealization hibernateInterfaceRealization = new HibernateInterfaceRealization();
        MyBatisInterfaseRealization myBatisInterfaseRealization = new MyBatisInterfaseRealization();
        hibernateInterfaceRealization.deleteAllStreets();

        long timeMilli11 = new Date().getTime();
        streetsForTest.forEach(x -> jdbsInterfaceRealization.saveStreet(x));
        long timeMilli12 = new Date().getTime();
        hibernateInterfaceRealization.deleteAllStreets();

        hibernateInterfaceRealization.getAllStreets();

        long timeMilli21 = new Date().getTime();
        streetsForTest.forEach(x -> hibernateInterfaceRealization.saveStreet(x));
        long timeMilli22 = new Date().getTime();
        hibernateInterfaceRealization.deleteAllStreets();

        long timeMilli31 = new Date().getTime();
        streetsForTest.forEach(x -> myBatisInterfaseRealization.saveStreet(x));
        long timeMilli32 = new Date().getTime();

        System.out.println("Add 100 entitites: result in miliseconds in jdbs method is ");
        System.out.println(timeMilli12 - timeMilli11);

        System.out.println("\n\nAdd 100 entitites: result in miliseconds in hibernate method is ");
        System.out.println(timeMilli22 - timeMilli21);

        System.out.println("\n\nAdd 100 entitites: result in miliseconds in mybatis method is ");
        System.out.println(timeMilli32 - timeMilli31);
    }

    @Test
    public void TestByGettingAllEntities() throws IOException, SQLException {
        String url = "jdbc:h2:mem:lab2;INIT=RUNSCRIPT FROM 'classpath:database_preparation.sql';";
        String user = "";
        String password = "";
        JDBSInterfaceRealization jdbsInterfaceRealization = new JDBSInterfaceRealization(url, user, password);
        HibernateInterfaceRealization hibernateInterfaceRealization = new HibernateInterfaceRealization();
        MyBatisInterfaseRealization myBatisInterfaseRealization = new MyBatisInterfaseRealization();

        long timeMilli11 = new Date().getTime();
        jdbsInterfaceRealization.getAllStreets();
        long timeMilli12 = new Date().getTime();

        long timeMilli21 = new Date().getTime();
        hibernateInterfaceRealization.getAllStreets();
        long timeMilli22 = new Date().getTime();

        long timeMilli31 = new Date().getTime();
        myBatisInterfaseRealization.getAllStreets();
        long timeMilli32 = new Date().getTime();

        System.out.println("Get all streets: result in miliseconds in jdbs method is ");
        System.out.println(timeMilli12 - timeMilli11);

        System.out.println("\n\nGet all streets: result in miliseconds in hibernate method is ");
        System.out.println(timeMilli22 - timeMilli21);

        System.out.println("\n\nGet all streets: result in miliseconds in mybatis method is ");
        System.out.println(timeMilli32 - timeMilli31);
    }
}
