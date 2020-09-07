package research;

import java.util.Objects;

public class BoughtRanking {
  private int rankingIndex;
  private Research rankingResearch;
  private int rankingPrice = 0;

  public BoughtRanking(int rankingIndex, Research rankingResearch, int rankingPrice) {
    this.rankingIndex = rankingIndex;
    this.rankingResearch = rankingResearch;
    this.rankingPrice = rankingPrice;
  }

  public int getRankingIndex() {
    return rankingIndex;
  }

  public Research getRankingResearch() {
    return rankingResearch;
  }

  public void setRankingResearch(Research rankingResearch) {
    this.rankingResearch = rankingResearch;
  }

  public int getRankingPrice() {
    return rankingPrice;
  }

  public void setRankingPrice(int rankingPrice) {
    this.rankingPrice = rankingPrice;
  }

  public void addRankingPrice(int price) {
    this.rankingPrice += price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BoughtRanking that = (BoughtRanking) o;
    return rankingIndex == that.rankingIndex;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rankingIndex);
  }
}
