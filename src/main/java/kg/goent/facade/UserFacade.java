package kg.goent.facade;


import kg.goent.dao.UserDao;
import kg.goent.models.User;

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

    public User findByEmail(String email){
        userDao.beginTransaction();
        User user;
        try {
            user = userDao.getEntityManager().createNamedQuery("User.findByEmail", kg.goent.models.User.class)
                    .setParameter("email", email).getSingleResult();
        }catch (Exception ex){
            user = null;
        }
        userDao.commitAndCloseTransaction();
        return user;
    }
    public User findByEmailPass(String email,String pass){
        userDao.beginTransaction();
        User user = userDao.getEntityManager().createNamedQuery("User.findByEmailPass",User.class)
                .setParameter("email",email).setParameter("password",pass).getSingleResult();
        userDao.closeTransaction();
        return user;

    }
}
