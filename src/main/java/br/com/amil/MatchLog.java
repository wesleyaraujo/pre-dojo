package br.com.amil;

import java.util.HashMap;
import java.util.Map;

public class MatchLog {
	private Integer matchId;
	private Integer totalKills;
	private Map<String, Integer> players = new HashMap<>();
	private Integer lineStart;

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId( Integer matchId ) {
		this.matchId = matchId;
	}

	public Integer getTotalKills() {
		return totalKills;
	}

	public void setTotalKills( Integer totalKills ) {
		this.totalKills = totalKills;
	}

	public Map<String, Integer> getPlayers() {
		return players;
	}

	public void setPlayers( Map<String, Integer> players ) {
		this.players = players;
	}

	public Integer getLineStart() {
		return lineStart;
	}

	public void setLineStart( Integer lineStart ) {
		this.lineStart = lineStart;
	}

}
