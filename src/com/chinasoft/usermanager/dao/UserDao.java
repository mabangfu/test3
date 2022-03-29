package com.chinasoft.usermanager.dao;

import com.chinasoft.usermanager.domain.PageInfo;
import com.chinasoft.usermanager.domain.User;
import com.chinasoft.usermanager.utils.JDBCUilts;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.beancontext.BeanContextProxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDao {
    private JdbcTemplate template =   new JdbcTemplate(JDBCUilts.getDataSource());

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll(){
        String sql = "select * from t_user order by id desc";
        return template.query(sql,new BeanPropertyRowMapper<>(User.class));
    }

    /**
     * 添加联系人
     */
    public void addUsers(User user){
        String sql = "insert into t_user(username,gender,age,address,qq,email,password) values(?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),"123456");
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Integer id){
        String sql = "delete from t_user where id = ?";
        template.update(sql,id);
    }

    /**
     * 修改用户
     * @param user
     */
    public void updateUser(User user){

        String sql = "update t_user set username = ?,gender = ?,age = ?,address = ?,qq = ?,email = ?,password = ? where id = ?";
        System.out.println(user.getId());
        template.update(sql,user.getUsername(),user.getGender(),
                user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getPassword(),user.getId());
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public User findOne(Integer id) {
        User user = null;
        try{
            String sql = "select * from t_user where id = ?";
            user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 查询用户信息做登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        try{
            String sql = "select * from t_user where username = ? and password = ?";
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 统计总记录数
     * @return
     */
    public Integer findTotalNum() {
        String sql = "select count(id) from t_user";
        return template.queryForObject(sql,Integer.class);//返回记录数
    }


    /**
     * 查询
     * @param condition
     * @return
     */

    public Integer findTotalNum(Map<String,String[]> condition){
        StringBuffer sql = new StringBuffer("select count(id) from t_user where 1 = 1");
        List<String> params = new ArrayList<>();
        Set<String> keySet = condition.keySet();
//        1.遍历map集合，查看搜索条件是否有值
        for(String key : keySet){
            if("current".equals(key) || "size".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if(value != null && "".equals(value)){
                sql.append(" and "+key+" like ? ");
//                list集合类型装参数
                params.add("%"+value+"%");
            }
        }
        return template.queryForObject(sql.toString(),Integer.class,params.toArray());
    }

    /**
     *  分页查询   select * from t_user where 1 = 1
     *               and  username like ?
     *               and  address like ?
     *               and  email like  ?
     *               order by id desc
     *               limit ?,?
     * @param start
     * @param size
     * @param condition
     * @return
     */
    public List<User> findUserByPage(Integer start, Integer size, Map<String, String[]> condition) {
        StringBuffer sql = new StringBuffer("select * from t_user where 1 = 1") ;
        List<Object> params = new ArrayList<>();
        Set<String> keySet = condition.keySet();
//        1.遍历map集合 查看搜索条件时候有值
        for (String key : keySet) {
            if ( "current".equals(key) || "size".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if ( value != null && !"".equals(value)){
                sql.append(" and "+key+" like ? ");
//        2.list集合类型装参数
                params.add("%"+value+"%");
            }
        }
        sql.append(" order by id desc ");
        sql.append(" limit ?,? ");



        params.add(start);
        params.add(size);
        return template.query(sql.toString(),new BeanPropertyRowMapper<>(User.class),params.toArray());
    }
}
