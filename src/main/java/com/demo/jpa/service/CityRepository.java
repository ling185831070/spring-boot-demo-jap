/*
 * Copyright 2012-2013 the original author or authors.
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


import com.demo.jpa.domian.City;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

interface CityRepository extends Repository<City, Long>{

	Page<City> findAll(Pageable pageable);

	Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
                                                                       String country, Pageable pageable);

	City findByNameAndCountryAllIgnoringCase(String name, String country);
	//===================>默认方法
	List<City> findAll();
	//使用此方法会出现一些问题（懒加载？？？），解决方案也会导致bean添加了一个懒加载属性，没弄清楚还是不用的好
	City getOne(long id);
	City findById(long id);
	void save(City city);
	Long delete(City city);
//	int count();
//	boolean exists(Long id);
	//<===================默认方法

}
