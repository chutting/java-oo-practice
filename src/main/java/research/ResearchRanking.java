package research;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ResearchRanking {
  private static LinkedList<Research> researchRankingList = new LinkedList<>();

  public static LinkedList<Research> getResearchRankingList() {
    return researchRankingList;
  }

  public static void setResearchRankingList(LinkedList<Research> researchRankingList) {
    ResearchRanking.researchRankingList = researchRankingList;
  }

  public static void addResearch(Research research) {
    researchRankingList.add(research);
  }

  public static List<Research> getSortedResearchRankingList() {
    Research boughtResearch = getBoughtResearch();

    List<Research> sortedUnBoughtResearchRankingList = researchRankingList.stream()
        .filter(research -> research.getResearchPrice() == 0)
        .sorted(((research1, research2) -> research2.getHeatValue() - research1.getHeatValue())).collect(Collectors.toList());

    if (boughtResearch != null) {
      sortedUnBoughtResearchRankingList.add(0 , boughtResearch);
    }

    return sortedUnBoughtResearchRankingList;
  }

  private static Research getBoughtResearch() {
    List<Research> boughtResearchList = researchRankingList.stream().filter(research -> research.getResearchPrice() != 0).collect(Collectors.toList());
    return boughtResearchList.size() == 0 ? null : boughtResearchList.get(0);
  }

  private static void removeResearch() {
    researchRankingList.remove(getBoughtResearch());
  }

  public static Research findResearchAccordingToName(String researchName) {
    List<Research> researchWithName = getResearchRankingList().stream()
        .filter(research -> research.getName().equals(researchName)).collect(Collectors.toList());

    if (researchWithName.size() == 0) {
      return null;
    }
    return researchWithName.get(0);
  }
}
