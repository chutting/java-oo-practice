package user;

import research.Research;
import research.MainResearchRankingList;

import java.util.List;
import java.util.Objects;

public class User {
  private String username;

  public User(String username) {
    this.username = username;
  }

  public void viewResearchRankingList() {
    List<Research> sortedResearchRankingList = MainResearchRankingList.getSortedResearchRankingList();

    for (int i = 0; i < sortedResearchRankingList.size(); i++) {
      System.out.println((i + 1) + " " + sortedResearchRankingList.get(i));
    }
  }

  public boolean addResearch(String researchName) {
    Research research = new Research(researchName);

    if (MainResearchRankingList.getResearchRankingList().contains(research)) {
      return false;
    }
    MainResearchRankingList.addResearch(research);
    return true;
  }

  public boolean isResearchNameExists(String researchName) {
    Research researchAccordingToName = MainResearchRankingList.findResearchAccordingToName(researchName);
    return researchAccordingToName != null;
  }

  public String getUsername() {
    return username;
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
