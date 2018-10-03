package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import controllers.Adsl;
import controllers.Telephone;
import controllers.User;
import controllers.User_Role;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("SELECT u FROM User u")
    public List<User> findName();
	
	@Query("SELECT Count(u) FROM User u WHERE u.username = :username")
    public Integer findCountLogin(@Param("username") String username);
	
	/**
	 * Получение списка пользователей имеющих указанный логин
	 * @param name логин пользователя
	 * @return пользователь
	 */
	@Query("SELECT u FROM User u WHERE u.username=:name")
    public User getUserInfo(@Param("name") String name);
	
	@Query("SELECT Distinct u FROM User u, in(u.user) ur, in(ur.role) r WHERE ur.user=u.id and ur.role=r.id and u.username=:name")
    public List<User> find(@Param("name") String name);
}