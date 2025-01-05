package com.harsha.Projectsuccess;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface Studentrepo extends CrudRepository<StudentEntity, Serializable> {
	    List<StudentEntity> findByName(String name);
	    List<StudentEntity> findByBranch(String branch);
	    List<StudentEntity> findByCollege(String college);
	    List<StudentEntity> findByCity(String city);
	    List<StudentEntity> findByGender(String gender);
	    List<StudentEntity> findBySkills(String skills);
		public Boolean deleteByName(String name);
		public Boolean deleteByBranch(String branch);
		public Boolean deleteByCollege(String college);
		public Boolean deleteByCity(String city);
		public Boolean deleteByGender(String gender);
		public Boolean deleteBySkills(String skills);

}
