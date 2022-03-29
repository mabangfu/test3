package com.chinasoft.usermanager.service.impl;

import com.chinasoft.usermanager.dao.UserDao;
import com.chinasoft.usermanager.domain.PageInfo;
import com.chinasoft.usermanager.domain.User;
import com.chinasoft.usermanager.service.UserService;
import com.chinasoft.usermanager.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDao();
    private Jedis jedis = JedisPoolUtil.getJedis();
    public List<User> findAll() {
        return userDao.findAll();
    }


    /**
     * 保存用户信息
     * @param user
     */
    @Override
    public void addUsers(User user) {
        userDao.addUsers(user);
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }


    @Override
    public User findOne(Integer id) {
        return userDao.findOne(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User login(String username, String password, UUID uuid) {
        String value = jedis.get(String.valueOf(uuid));
        if(value != null){
            System.out.println("value != null");
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
            return u;
        }else{
            System.out.println("value == null");
            jedis.setex("UUID",60, String.valueOf(uuid));
            User user = userDao.login(username, password);
            if(user != null){
                jedis.setex(username,60,password);
            }
            return user;
        }
    }


    @Override
    public PageInfo<User> findUserByPage(Integer size, Integer current, Map<String, String[]> condition) {
        PageInfo<User> pageInfo = new PageInfo<>();
//        1.统计记录总数
        Integer total = userDao.findTotalNum(condition);
//        3.计算总页数
        Integer pageNum = total%size == 0 ? total : (total/size)+1;
        //        2.分页查询记录  select * from t_user limit x,size; (current-1)*size
        if(current <= 1){
            current = 1;
        }else if(current >= pageNum){
            current = pageNum;
        }
        int start = (current-1)*size;
        List<User> list = userDao.findUserByPage(start,size,condition);
//        4.组装pageInfo对象
        pageInfo.setCurrent(current);
        pageInfo.setList(list);
        pageInfo.setPageNum(pageNum);
        pageInfo.setTotal(total);
        pageInfo.setSize(size);
        return pageInfo;
    }

}
