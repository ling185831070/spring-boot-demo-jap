package com.demo.jpa.dao.city;

import com.demo.jpa.domian.City;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * 继承此类可以得到默认的操作方法
 */


public interface CityJpaRepositoryImplementation extends JpaRepositoryImplementation<City,Long> {
}
