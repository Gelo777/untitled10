package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> getUsers() {
		return em.createQuery("SELECT u from User u", User.class).getResultList();
	}
	
	@Override
	public void createUser(User user) {
		em.persist(user);
	}
	
	@Override
	public User getUserById(Long id) {
		return em.find(User.class, id);
	}
	
	@Override
	public void updateUser(User user) {
		em.merge(user);
	}
	
	@Override
	public void deleteUser(Long id) {
		
		em.createQuery("DELETE FROM User WHERE id= :id").setParameter("id", id).executeUpdate();
	}
}
