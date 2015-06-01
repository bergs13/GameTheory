/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.general;

/**
 *
 * @author Thunderchild
 */
public class Move {
	// Members
	Object move = null;

	// Constructors
	public Move(Object move) {
		this.move = move;
	}

	// Methods
	public void executeMove(Object state) {
	}
	protected Object getMove()
	{
		return this.move;
	}
}