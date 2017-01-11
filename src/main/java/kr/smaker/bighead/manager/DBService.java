package kr.smaker.bighead.manager;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void insertCode(HashMap<String, Object> map) throws Exception {
		sqlSession.insert("userMapper.insertCode", map);
	}
}
