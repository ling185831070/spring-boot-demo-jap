/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.demo.jpa.service;

import com.demo.jpa.dao.city.CityJpaRepositoryImplementation;
import com.demo.jpa.domian.City;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link CityRepository}.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryIntegrationTests {

    @Autowired
    CityRepository repository;

    @Autowired
    CityJpaRepositoryImplementation cjri;


    @Test
    public void test(){

    }



    @Test
    public void findsFirstPageOfCities() {
        Page<City> cities = this.repository.findAll(PageRequest.of(0, 10));
        assertThat(cities.getTotalElements()).isGreaterThan(20L);
        cities.forEach(e -> System.out.println(e + e.getName() + "********************************************************************"));

    }

    @Test
    public void findAll() {
        List<City> all = this.repository.findAll();
        all.forEach(e -> System.out.println(e.getName() + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"));
    }


    @Test
    @Transactional(readOnly = true)
    public void findOne() {
        City one = this.repository.getOne(1);
       System.out.println(one.getCountry());
    }

    @Before
    public void save() {
        City city = new City("北京", "中国");
        city.setId(100L);
        city.setMap("341234,4879875t");
        city.setState("5626");
//        insert into city (country, map, name, state, id) values (?, ?, ?, ?, ?)
     this.repository.save(city);
    }

    @Test
    @Transactional
    public void delete() {
        City city = new City("北京", "中国");
        city.setId(100L);
        city.setMap("341234,4879875t");
        city.setState("5626");
//        delete from city where id=?
        Long delete = this.repository.delete(city);
        System.out.println(delete+"****************************");
    }
/*
    @Test
    public void count() {
        int count = this.repository.count();
        System.out.println(count + "?????????????????????????????????????????????????????");
    }

    @Test
    public void exists() {
        boolean exists = this.repository.exists(1L);
        System.out.println(exists + "{}{}{{{{{{{{{{{{{{{{{{{{{{{{{{{{{");
    }*/

}
