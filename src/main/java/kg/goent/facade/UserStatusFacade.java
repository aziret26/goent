package kg.goent.facade;


import kg.goent.dao.ObjectDao;
import kg.goent.models.UserStatus;

import java.util.List;

public class UserStatusFacade {
    private ObjectDao objectDao = new ObjectDao();

    public UserStatusFacade(){
        if(findAll().size() == 0){
            initializeUserStatuses();
        }
    }

    public void createUserStatus(UserStatus userStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(userStatus);
        objectDao.commitAndCloseTransaction();
    }

    public void updateUserStatus(UserStatus userStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(userStatus);
        objectDao.commitAndCloseTransaction();
    }

    public void deleteUserStatus(UserStatus userStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(userStatus);
        objectDao.commitAndCloseTransaction();
    }

    public List<UserStatus> findAll(){
        objectDao.beginTransaction();
        List<UserStatus> userStatusesList = objectDao.getEntityManager().
                createNamedQuery("UserStatus.findAll",UserStatus.class).getResultList();
        objectDao.closeTransaction();
        return userStatusesList;
    }
    public UserStatus findById(Integer id) {
        objectDao.beginTransaction();
        UserStatus userStatus = objectDao.getEntityManager().find(UserStatus.class, id);
        objectDao.commitAndCloseTransaction();
        return userStatus;
    }
    private void initializeUserStatuses(){
        UserStatus us;
        us = new UserStatus("activated");
        createUserStatus(us);
        us = new UserStatus("banned");
        createUserStatus(us);
        us = new UserStatus("frozen");
        createUserStatus(us);
        us = new UserStatus("not activated");
        createUserStatus(us);
    }
}
