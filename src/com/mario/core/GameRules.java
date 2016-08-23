package com.mario.core;

import com.mario.model.User;

public interface GameRules {

	boolean apply(User player);
	
}
