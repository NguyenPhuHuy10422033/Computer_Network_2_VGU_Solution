package TCP_multithread_assignment_5;

public class GuessingGame {
    private int goal;
    private boolean correct;
  
    public GuessingGame()
    {
      goal = (int) (Math.random() * 9 + 1);
      correct = false;
     }
  
    public int getGoal()
    {
      return goal;
    }
    
    public boolean getCorrect()
    {
      return correct;
    }
  
  public String guess(int guess)
  {
  if(guess == goal)
    {
    correct = true;
    return "Well done, your answer is correct";
    }
  else if(guess > goal)
  {
    return "Too high, can you try it with another number?";
  }
  
  else return "Too low, you can try to pick up more bigger number please";
      }
  }