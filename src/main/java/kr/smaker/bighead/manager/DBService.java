package kr.smaker.bighead.manager;

import java.util.HashMap;
import java.util.List;

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
	
	public String selectImei(String param) throws Exception {
		return sqlSession.selectOne("userMapper.selectImei", param);
	}
	
	public List<HashMap<String, Object>> selectList() throws Exception {
		return sqlSession.selectList("userMapper.selectList");
	}
}
