package antywypok.dao;

import antywypok.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {
	
	public Vote getVoteByUserIdDiscoveryId(long userId, long discoverId);
}
