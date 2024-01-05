package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import com.parking.parkinglot.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    @Inject
    private PasswordBean passwordBean;

    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers");
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    private static List<UserDto> copyUsersToDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getEmail(), user.getUsername(), user.getPassword()))
                .collect(java.util.stream.Collectors.toList());
    }
    public void createUser(String username, String email, String password, Collection<String> groups) {
        LOG.info("createUser");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));
        entityManager.persist(newUser);
        assignGroupsToUser(username, groups);
    }
    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }
    public Collection<String> findUsernamesByUserIds(Collection<Long> userIds) {
        return entityManager.createQuery("SELECT u.username FROM User u WHERE u.id IN :userIds", String.class)
                .setParameter("userIds", userIds)
                .getResultList();
    }
    public UserDto findById(Long userId) {
        User user = entityManager.find(User.class, userId);
        return new UserDto(user.getId(), user.getEmail(), user.getUsername(), user.getPassword());
    }
    public void updateUserWithPassword(Long userId, String username, String email, String password) {
        LOG.info("updateUserWithPassword");

        User user = entityManager.find(User.class, userId);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordBean.convertToSha256(password));

        entityManager.persist(user);
    }

    public void updateUserWithoutPassword(Long userId, String username, String email) {
        LOG.info("updateUserWithoutPassword");

        User user = entityManager.find(User.class, userId);
        user.setUsername(username);
        user.setEmail(email);

        entityManager.persist(user);
    }
}

