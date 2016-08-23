package com.mario.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mario.persistence.RiddleDao;

public class RiddleDaoImpl implements RiddleDao {

	Connection dbConnection;

	public RiddleDaoImpl(Connection db) {
		this.dbConnection = db;
	}

	@Override
	public String getRiddle(int level) {
		try {
			String getRiddlesql = "select question from riddles where difficulty=?";
			PreparedStatement stmt = dbConnection.prepareStatement(getRiddlesql);
			stmt.setInt(1, level);
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				return results.getString("question");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkRiddleAnswer(String answer, int level) {
		return answer.equalsIgnoreCase(getAnswer(level));
	}

	private String getAnswer(int level) {
		try {
			String getRiddlesql = "select answer from riddles where difficulty=?";
			PreparedStatement stmt = dbConnection.prepareStatement(getRiddlesql);
			stmt.setInt(1, level);
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				return results.getString("answer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getRiddleHint(Integer level) {
		String answer = getAnswer(level);
		StringBuilder hint = new StringBuilder();
		for (int i = 0; i < answer.length(); i++) {
			int seed = i+answer.length();
			if ((((int)Math.random()*100)+seed)%2==0) {
				hint.append(answer.charAt(i)+" ");
			} else {
				hint.append("_ ");
			}
		}
		return hint.toString();
	}

}
