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

package com.demo.jpa.dao.city;


import com.demo.jpa.domian.City;
import com.demo.jpa.domian.HotelSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 自定义简单查询：根据方法名自动生成SQL
 * 主要：findXXXBy、readXXXBy、queryXXXBy、getXXXBy、countByName
 * 后面跟属性名称，关键字之类的
 */
interface CityRepository extends Repository<City, Long>{
	//==============》分页查询
	Page<City> findAll(Pageable pageable);

	Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name, String country, Pageable pageable);
	//《==============分页查询


	//==============》限制查询
	City findByNameAndCountryAllIgnoringCase(String name, String country);

	City findFirstByOrderByLastNameAsc();

	City findTopByOrderByAgeDesc();

	Page<City> queryFirst10ByLastname(String lastname,Pageable pageable);

	List<City> findFirst10ByLastname(String lastname,Sort sort);

	List<City> findTop10ByLastname(String lastname,Pageable pageable);
	//《==============限制查询

	//==============》自定义SQL查询
	@Modifying
	@Query("update City c set c.name=?1 where c.id= ?2")
	int modifyByIdAndUserId(String name,Long id);

	@Transactional
	@Modifying
	@Query("delete  from City where id= ?1")
	void deleteByCityId(Long id);

	@Transactional(timeout = 10)
	@Query("select u from City c where c.country= ?1")
	City findByCountry(String country);
	//《==============自定义SQL查询

	//==============》多表查询
	@Query("select h.city as city, h.name as name, avg(r.rating) as averageRating from Hotel h left outer join h.reviews r where h.city= ?1 group by h")
	Page<HotelSummary> findByCity(City city,Pageable pageable);

	@Query("select h.name as name,avg(r.rating) as averageRating from Hotel h left outer join h.reviews r group by h")
	Page<HotelSummary> findByCity(Pageable pageable);
	//《==============多表查询

}
