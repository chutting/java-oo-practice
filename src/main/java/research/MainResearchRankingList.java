package research;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MainResearchRankingList {
  private static LinkedList<Research> researchRankingList = new LinkedList<>();
  private static LinkedList<BoughtRanking> boughtRankingList = new LinkedList<>();

  private static final String BUY_RANKING_SUCCESS = "购买成功";
  private static final String BUY_RANKING_FAILURE_MONEY_IS_NOT_ENOUGH = "购买失败，当前位置的价格为%d";
  private static final String BUY_RANKING_PRICE_ACCUMULATED = "拍卖价格累加成功";


  public static LinkedList<Research> getResearchRankingList() {
    return researchRankingList;
  }

  public static void addResearch(Research research) {
    researchRankingList.add(research);
  }

  public static String buyRanking(String researchName, int price, int rankingIndex) {
    Research research = findResearchAccordingToName(researchName);
    BoughtRanking boughtRanking = new BoughtRanking(rankingIndex, research, price);

    if (boughtRankingList.contains(boughtRanking)) {
      BoughtRanking existedBoughtRanking = boughtRankingList.stream().filter(boughtRanking::equals).collect(Collectors.toList()).get(0);

      if (existedBoughtRanking.getRankingResearch().equals(research)) {
        existedBoughtRanking.addRankingPrice(price);
        return BUY_RANKING_PRICE_ACCUMULATED;
      } else {
        System.out.println(researchRankingList);
        return isPriceEnough(existedBoughtRanking, research, price) ? BUY_RANKING_SUCCESS : String.format(BUY_RANKING_FAILURE_MONEY_IS_NOT_ENOUGH, existedBoughtRanking.getRankingPrice());
      }
    } else {
      boughtRankingList.add(boughtRanking);
      System.out.println(researchRankingList);
      return BUY_RANKING_SUCCESS;
    }
  }

  private static boolean isPriceEnough(BoughtRanking existedBoughtRanking, Research newBoughtResearch, int price) {
    if (existedBoughtRanking.getRankingPrice() >= price) {
      return false;
    } else {
      removeResearch(existedBoughtRanking.getRankingResearch());
      existedBoughtRanking.setRankingPrice(price);
      existedBoughtRanking.setRankingResearch(newBoughtResearch);
      return true;
    }
  }

  public static List<Research> getSortedResearchRankingList() {
    List<Research> sortedResearchList = researchRankingList.stream()
        .filter(research -> !(getBoughtResearch().contains(research)))
        .sorted(((research1, research2) -> research2.getHeatValue() - research1.getHeatValue()))
        .collect(Collectors.toList());

    boughtRankingList.stream().forEach(boughtRanking -> {
      sortedResearchList.add(boughtRanking.getRankingIndex() - 1, boughtRanking.getRankingResearch());
    });

    return sortedResearchList;
  }

  private static List<Research> getBoughtResearch() {
    List<Research> boughtResearch = boughtRankingList.stream()
        .map(boughtRanking -> boughtRanking.getRankingResearch())
        .collect(Collectors.toList());
    return boughtResearch;
  }

  private static void removeResearch(Research research) {
    researchRankingList.remove(research);
  }

  public static Research findResearchAccordingToName(String researchName) {
    List<Research> researchWithName = researchRankingList.stream()
        .filter(research -> research.getName().equals(researchName)).collect(Collectors.toList());

    if (researchWithName.size() == 0) {
      return null;
    }
    return researchWithName.get(0);
  }
}
