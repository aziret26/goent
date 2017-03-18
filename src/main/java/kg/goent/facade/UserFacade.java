package kg.goent.facade;


import kg.goent.dao.UserDao;
import kg.goent.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserFacade {

    private UserDao userDao = new UserDao();

    public void createUser(User user) {
        userDao.beginTransaction();
        userDao.getEntityManager().persist(user);
        userDao.commitAndCloseTransaction();
    }

    public void updateUser(User user) {
        userDao.beginTransaction();
        userDao.getEntityManager().merge(user);
        userDao.commitAndCloseTransaction();
    }

    public void deleteUser(User user) {
        userDao.beginTransaction();
        userDao.getEntityManager().remove(user);
        userDao.commitAndCloseTransaction();
    }

    public User findById(Integer id) {
        userDao.beginTransaction();
        User user = userDao.getEntityManager().find(User.class, id);
        userDao.commitAndCloseTransaction();
        return user;
    }
    public List<User> getAll(){
        List<User> users = new ArrayList<User>();
        userDao.beginTransaction();
        users = userDao.getEntityManager().createNamedQuery("User.findAll",User.class).getResultList();
        userDao.commitAndCloseTransaction();
        return users;
    }
}
