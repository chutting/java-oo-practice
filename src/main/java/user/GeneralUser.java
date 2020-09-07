package user;

import research.Research;
import research.MainResearchRankingList;

public class GeneralUser extends User{

  private int remainingVoteNum = 10;

  public GeneralUser(String username) {
    super(username);
  }

  public void vote(String voteName, int voteNum) {
    Research researchAccordingToName = MainResearchRankingList.findResearchAccordingToName(voteName);
    researchAccordingToName.vote(voteNum);
    remainingVoteNum -= voteNum;
  }

  public String buy(String researchName, int price, int rankingIndex) {
    String buyRankingPrompt = MainResearchRankingList.buyRanking(researchName, price, rankingIndex);
    return buyRankingPrompt;
  }

  public int getRemainingVoteNum() {
    return remainingVoteNum;
  }
}
