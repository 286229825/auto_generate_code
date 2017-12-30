package generateTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import mapper.UserMapper;
import po.User;

class CodeGeneratorTest {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		InputStream inputStream=null;
		try {
			inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	void insertOne() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		userMapper.insertOne(new User(null,"FF", 16, "男", "学生", new java.sql.Date(new Date().getTime()), 1,0,2));
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	void insertBatch() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		List<User> list=new ArrayList<>();
		for (int i = 22; i < 26; i++) {
			list.add(new User(null,"GG", i, "男", "学生", new java.sql.Date(new Date().getTime()), 1,0,2));
		}
		userMapper.insertBatch(list);
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	void deleteOne() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		userMapper.deleteOne(10);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	void deleteBatch() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		List<Integer> list=new ArrayList<>();
		for (int i = 7; i < 10; i++) {
			list.add(i);
		}
		userMapper.deleteBatch(list);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	void updateOne() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		userMapper.updateOne(new User(6,"FF", 20, "男", "学生", new java.sql.Date(new Date().getTime()), 1,0,2));
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	void updateBatch() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		List<User> list=new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			list.add(new User(i,"GG", i, "男", "学生", new java.sql.Date(new Date().getTime()), 1,0,2));
		}
		userMapper.updateBatch(list);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	void selectByCondition() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.selectByCondition(new User(null, null,16, "男", null, null, null,0,2));
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	void selectByPage() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setRows(2);
		user.setStartRow(0);
		user.setSex("男");
		List<User> list = userMapper.selectByPage(user);
		sqlSession.commit();
		sqlSession.close();
	}
	
}
