package user;

import research.Research;
import research.ResearchRanking;

public class Administrator extends User{
  private String password;

  public Administrator() {
    super("admin");
    this.password = "admin";
  }

  public void addSuperResearch(String researchName) {
    Research researchAccordingToName = ResearchRanking.findResearchAccordingToName(researchName);
    if (researchAccordingToName == null) {
      ResearchRanking.addResearch(new Research(researchName, true));
    } else {
      researchAccordingToName.setSuperResearch(true);
    }
  }

  public boolean isAdminAccountCorrect(String username, String password) {
    return (username.equals(this.getUsername()) && password.equals(this.password));
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
