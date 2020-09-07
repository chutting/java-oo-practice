package user;

import research.Research;
import research.ResearchRanking;

public class GeneralUser extends User{

  private int remainingVoteNum = 10;

  public GeneralUser(String username) {
    super(username);
  }

  public void vote(String voteName, int voteNum) {
    Research researchAccordingToName = ResearchRanking.findResearchAccordingToName(voteName);
    researchAccordingToName.vote(voteNum);
    remainingVoteNum -= voteNum;
  }

  public void buy(String researchName, int price, int ranking) {

  }

  public int getRemainingVoteNum() {
    return remainingVoteNum;
  }

  public void setRemainingVoteNum(int remainingVoteNum) {
    this.remainingVoteNum = remainingVoteNum;
  }
}
