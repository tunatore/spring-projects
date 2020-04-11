package springmvc.java.dao;

import org.springframework.data.repository.CrudRepository;

import springmvc.java.domain.User;

/**
 * Spring Data JPA Api will generate the implementation of this interface
 *
 */
public interface UserDAO extends CrudRepository<User, Long> {

	User findUserByUsername(String username);
}
