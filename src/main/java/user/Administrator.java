package user;

import research.Research;
import research.MainResearchRankingList;

public class Administrator extends User{
  private String password;

  public Administrator() {
    super("admin");
    this.password = "admin";
  }

  public void addSuperResearch(String researchName) {
    Research researchAccordingToName = MainResearchRankingList.findResearchAccordingToName(researchName);
    if (researchAccordingToName == null) {
      MainResearchRankingList.addResearch(new Research(researchName, true));
    } else {
      researchAccordingToName.setSuperResearch(true);
    }
  }

  public boolean isAdminAccountCorrect(String username, String password) {
    return (username.equals(this.getUsername()) && password.equals(this.password));
  }
}
