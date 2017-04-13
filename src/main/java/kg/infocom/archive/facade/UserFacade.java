package kg.infocom.archive.facade;


import kg.infocom.archive.dao.UserDao;
import kg.infocom.archive.domain.User;

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
}
