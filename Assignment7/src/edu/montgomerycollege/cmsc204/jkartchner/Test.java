package edu.montgomerycollege.cmsc204.jkartchner;

/**
 * Test holds all questions and answers for a test
 * Methods to check if answer is correct,
 * get next question, and check the percent of correct answers
 * @author Professor Myers
 *
 */
public abstract class Test {
	protected double percentCorrect;
	protected int numOfQuestions;
	protected int numOfCorrect;
	protected int currentQuestion;
	protected int currentAnsweredQuestion;
	protected String[] questions;
	protected String[] answers;

	/**
	 * Constructor for the class Test
	 * @param quest an array of Strings that are the questions of the test
	 * @param answ an array of Strings that are the answers to the questions
	 */

	public Test(String[] quest, String[] answ) {
		questions = quest;
		answers = answ;
		percentCorrect = 0;
		numOfQuestions = 10;
		currentQuestion = 0;
		numOfCorrect = 0;
		currentAnsweredQuestion = 0;
	}

	/**
	 * Constructor for the class Test to let subclass set the questions and answers
	 * 
	 */
	public Test() {
		questions = null;
		answers = null;
		percentCorrect = 0;
		numOfQuestions = 10;
		currentQuestion = 0;
		numOfCorrect = 0;
		currentAnsweredQuestion = 0;
	}

	/**
	 * Checks if the answer given is the correct answer to the question
	 * currently being asked
	 * @param testTakerAnswer answer by test taker to check against correct answer
	 * @return Correct if testTakerAnswer is correct
	 * 		 Incorrect if testTakerAnswer is incorrect
	 */
	public abstract String check(String testTakerAnswer);

	/**
	 * Returns the next question in the test bank
	 * @return the next question in the test bank
	 */
	public abstract String next();
	/**
	 * Returns the percentage of answers test taker has answered correctly
	 * @return percentage of correct answers
	 */
	public abstract double getPercentCorrect();


}
