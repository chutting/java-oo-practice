package user;

import research.Research;
import research.ResearchRanking;

import java.util.List;
import java.util.Objects;

public class User {
  private String username;

  public User(String username) {
    this.username = username;
  }

  public void viewResearchRankingList() {
    List<Research> sortedResearchRankingList = ResearchRanking.getSortedResearchRankingList();

    for (int i = 0; i < sortedResearchRankingList.size(); i++) {
      System.out.println((i + 1) + " " + sortedResearchRankingList.get(i));
    }
  }

  public boolean addResearch(String researchName) {
    Research research = new Research(researchName);

    if (ResearchRanking.getResearchRankingList().contains(research)) {
      return false;
    }
    ResearchRanking.addResearch(research);
    return true;
  }

  public boolean isResearchNameExists(String researchName) {
    Research researchAccordingToName = ResearchRanking.findResearchAccordingToName(researchName);
    if (researchAccordingToName == null) {
      return false;
    }
    return true;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(username, user.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username);
  }
}
