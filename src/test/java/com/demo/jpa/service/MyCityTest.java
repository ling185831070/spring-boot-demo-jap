package com.demo.jpa.service;

import com.demo.jpa.dao.city.CityJpaRepositoryImplementation;
import com.demo.jpa.domian.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyCityTest {
    @Autowired
    CityJpaRepositoryImplementation c;

    @Test
    @Transactional
    public void testDefault(){
        City city = new City("北京", "中国");
        city.setId(100L);
        city.setMap("341234,4879875t");
        city.setState("5626");

        System.out.println(c.save(city));
        c.delete(city);
        System.out.println(c.count());
        System.out.println(c.existsById(100L));

    }

}
