package research;

import java.util.Objects;

public class Research {
  private String name;
  private int voteNum = 0;
  private int boughtRanking = 0;
  private int researchPrice = 0;
  private boolean isSuperResearch = false;

  public Research(String name) {
    this.name = name;
  }

  public Research(String name, boolean isSuperResearch) {
    this.name = name;
    this.isSuperResearch = isSuperResearch;
  }

  public int getHeatValue() {
    return isSuperResearch ? this.voteNum * 2 : this.voteNum;
  }

  public void vote(int voteNum) {
    this.voteNum += voteNum;
  }

  public void buyResearch(int price, int boughtRanking) {
    this.boughtRanking = boughtRanking;
    this.researchPrice += price;
  }

  public String getName() {
    return name;
  }

  public boolean isSuperResearch() {
    return isSuperResearch;
  }

  public void setSuperResearch(boolean superResearch) {
    isSuperResearch = superResearch;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getResearchPrice() {
    return researchPrice;
  }

  public void setResearchPrice(int researchPrice) {
    this.researchPrice = researchPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Research research = (Research) o;
    return Objects.equals(name, research.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return this.name + " " + this.getHeatValue();
  }
}