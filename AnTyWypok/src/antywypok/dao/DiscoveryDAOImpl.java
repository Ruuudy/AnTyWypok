package antywypok.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import antywypok.model.Discovery;
import antywypok.model.User;
import antywypok.util.ConnectionProvider;

public class DiscoveryDAOImpl implements DiscoveryDAO {
	
	private static final String CREATE_DISCOVERY = "INSERT INTO discovery(name, description, url, user_id, date, up_vote, down_vote) " + "VALUES(:name, :description, :url, :user_id, :date, :up_vote, :down_vote);";
	
	private static final String READ_ALL_DISCOVERIES = "SELECT user.user_id, username, email, is_active, password, discovery_id, name, description, url, date, up_vote, down_vote " + "FROM discovery LEFT JOIN user ON discovery.user_id=user.user_id;";
		
	private NamedParameterJdbcTemplate template;
	
	public DiscoveryDAOImpl() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	}
	
	@Override
	public Discovery create(Discovery discovery){
		Discovery resultDiscovery = new Discovery(discovery);
		KeyHolder holder = new GeneratedKeyHolder();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", discovery.getName());
		paramMap.put("description", discovery.getDescription());
		paramMap.put("url", discovery.getUrl());
		paramMap.put("user_id", discovery.getUser().getId());
		paramMap.put("date", discovery.getTimestamp());
		paramMap.put("up_vote", discovery.getUpVote());
		paramMap.put("down_vote", discovery.getDownVote());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(CREATE_DISCOVERY, paramSource, holder);
		if(update > 0){
			resultDiscovery.setId((Long)holder.getKey());
		}
		return resultDiscovery;
	}
	
	@Override
    public Discovery read(Long primaryKey) {
        return null;
    }
 
    @Override
    public boolean update(Discovery updateObject) {
        return false;
    }
 
    @Override
    public boolean delete(Long key) {
        return false;
    }
 
    @Override
    public List<Discovery> getAll() {
        List<Discovery>  discoveries = template.query(READ_ALL_DISCOVERIES, new DiscoveryRowMapper());
        return discoveries;
    }
    
    private class DiscoveryRowMapper implements RowMapper<Discovery>{
    	@Override
    	public Discovery mapRow(ResultSet rs, int rowNum) throws SQLException{
    		Discovery discovery = new Discovery();
    		discovery.setId(rs.getLong("discovery_id"));
    		discovery.setName(rs.getString("name"));
    		discovery.setName(rs.getString("description"));
    		discovery.setUrl(rs.getString("url"));
    		discovery.setUpVote(rs.getInt("up_vote"));
    		discovery.setDownVote(rs.getInt("down_vote"));
    		discovery.setTimestamp(rs.getTimestamp("date"));
    		User user = new User();
    		user.setId(rs.getLong("user_id"));
    		user.setUsername(rs.getString("username"));
    		user.setEmail(rs.getString("email"));
    		user.setPassword(rs.getString("password"));
    		discovery.setUser(user);
    		return discovery;
    	}
    }
}
