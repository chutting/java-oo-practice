import research.MainResearchRankingList;
import user.Administrator;
import user.GeneralUser;
import user.User;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
  private static final String INITIAL_INTERFACE = "欢迎来到热搜排行榜，您是？\n1. 用户\n2. 管理员\n3. 退出";
  private static final String PLEASE_INPUT_USERNAME = "请输入您的昵称:";
  private static final String PLEASE_INPUT_PASSWORD = "请输入您的密码:";
  private static final String USER_INTERFACE = "您好，%s,您可以：\n1. 查看热搜排行榜\n2. 给热搜事件投票\n3. 购买热搜\n4. 添加热搜\n5. 退出";
  private static final String ADMIN_INTERFACE = "您好，%s，您可以：\n1. 查看热搜排行榜\n2. 添加热搜\n3. 添加超级热搜\n4. 退出";
  private static final String PLEASE_INPUT_VOTE_RESEARCH_NAME = "请输入您要投票的热搜名字:";
  private static final String PLEASE_INPUT_VOTE_NUM = "请输入您要投票的票数(您的剩余票数为%d张):";
  private static final String PLEASE_INPUT_RESEARCH_NAME_YOU_WANT_TO_ADD = "请输入您要添加的热搜名字:";
  private static final String PLEASE_INPUT_SUPER_RESEARCH_NAME_YOU_WANT_TO_ADD = "请输入您要添加的超级热搜名字:";
  private static final String ADD_FAILURE = "添加热搜失败，您添加的热搜已存在";
  private static final String ADD_SUCCESS = "添加热搜成功";
  private static final String YOUR_ADMIN_ACCOUNT_IS_INCORRECT = "您的管理员账号或密码错误，请重新输入";
  private static final String THE_INPUT_INDEX_IS_INCORRECT = "您输入的序号错误，请重新输入";
  private static final String VOTE_FAILURE_RESEARCH_NOT_EXISTS = "您投票的热搜不存在，请重新输入";
  private static final String VOTE_FAILURE_VOTE_NUM_NOT_ENOUGH = "您的票数不够,请重新输入";
  private static final String VOTE_SUCCESS = "投票成功";
  private static final String VOTE_NUM_IS_ZERO = "您的票数已为0，无法投票";
  private static final String PLEASE_INPUT_BOUGHT_RANKING_RESEARCH_NAME = "请输入您想购买的热搜名称";
  private static final String BUY_RANKING_FAILURE_RESEARCH_NOT_EXISTS = "您想购买的热搜不存在，请重新输入";
  private static final String PLEASE_INPUT_THE_RANKING_YOU_WANT_TO_BUY = "请输入您想购买的热搜排名";
  private static final String PLEASE_INPUT_THE_PRICE_YOU_WANT_TO_COST = "请输入您要购买的热搜金额";
  private static final String RANKING_INDEX_IS_OUT_OF_RANGE = "您想购买的排位超出范围";



  private static LinkedList<GeneralUser> userList = new LinkedList<>();

  public static void show() {
    System.out.println(INITIAL_INTERFACE);
    int index = inputInt();
    switch (index) {
      case 1:
        showUserInterface();
        break;
      case 2:
        showAdminInterface();
        break;
      case 3:
        break;
      default:
        System.out.println(THE_INPUT_INDEX_IS_INCORRECT);
        show();
    }
  }

  private static String inputString() {
    Scanner inputScanner = new Scanner(System.in);
    return inputScanner.next();
  }

  private static int inputInt() {
    Scanner inputScanner = new Scanner(System.in);
    return inputScanner.nextInt();
  }

  private static void showUserInterface() {
    System.out.println(PLEASE_INPUT_USERNAME);
    String username = inputString();
    GeneralUser generalUser = new GeneralUser(username);
    if (!userList.contains(generalUser)) {
      userList.add(generalUser);
    } else {
      generalUser = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList()).get(0);
    }
    userInterface(generalUser);
  }

  private static void userInterface(GeneralUser user) {
    System.out.println(String.format(USER_INTERFACE, user.getUsername()));
    int index = inputInt();
    userOption(index, user);
  }

  private static void userOption(int index, GeneralUser user) {
    switch (index) {
      case 1:
        user.viewResearchRankingList();
        userInterface(user);
        break;
      case 2:
        if (user.getRemainingVoteNum() == 0) {
          System.out.println(VOTE_NUM_IS_ZERO);
          userInterface(user);
        }
        inputVoteName(user);
        break;
      case 3:
        buyRanking(user);
        break;
      case 4:
        addResearch(user);
        userInterface(user);
        break;
      case 5:
        show();
        break;
      default:
        System.out.println(THE_INPUT_INDEX_IS_INCORRECT);
        userInterface(user);
    }
  }

  private static void buyRanking(GeneralUser user) {
    System.out.println(PLEASE_INPUT_BOUGHT_RANKING_RESEARCH_NAME);
    String boughtRankingResearchName = inputString();
    boolean isVoteResearchNameExists = user.isResearchNameExists(boughtRankingResearchName);
    if (isVoteResearchNameExists) {
      inputBoughtRankingAndPrice(boughtRankingResearchName, user);
    } else {
      System.out.println(BUY_RANKING_FAILURE_RESEARCH_NOT_EXISTS);
      userInterface(user);
    }
  }

  private static void inputBoughtRankingAndPrice(String boughtRankingResearchName, GeneralUser user) {
    System.out.println(PLEASE_INPUT_THE_RANKING_YOU_WANT_TO_BUY);
    int ranking = inputInt();
    System.out.println(PLEASE_INPUT_THE_PRICE_YOU_WANT_TO_COST);
    int price = inputInt();
    if (isBoughtRankingLegal(ranking)) {
      String buyRankingPrompt = user.buy(boughtRankingResearchName, price, ranking);
      System.out.println(buyRankingPrompt);
    } else {
      System.out.println(RANKING_INDEX_IS_OUT_OF_RANGE);
    }
    userInterface(user);
  }

  private static boolean isBoughtRankingLegal(int ranking) {
    if (ranking > 0 && ranking <= MainResearchRankingList.getResearchRankingList().size()) {
      return true;
    }
    return false;
  }

  private static void inputVoteName(GeneralUser user) {
    System.out.println(PLEASE_INPUT_VOTE_RESEARCH_NAME);
    String voteResearchName = inputString();
    boolean isVoteResearchNameExists = user.isResearchNameExists(voteResearchName);
    if (isVoteResearchNameExists) {
      inputVoteNum(voteResearchName, user);
    } else {
      System.out.println(VOTE_FAILURE_RESEARCH_NOT_EXISTS);
      userOption(2, user);
    }
  }

  private static void inputVoteNum(String voteResearchName, GeneralUser user) {
    System.out.println(String.format(PLEASE_INPUT_VOTE_NUM, user.getRemainingVoteNum()));
    int voteNum = inputInt();
    if (voteNum > user.getRemainingVoteNum()) {
      System.out.println(VOTE_FAILURE_VOTE_NUM_NOT_ENOUGH);
      inputVoteNum(voteResearchName, user);
    } else {
      user.vote(voteResearchName, voteNum);
      System.out.println(VOTE_SUCCESS);
      userInterface(user);
    }
  }

  private static void addResearch(User user) {
    System.out.println(PLEASE_INPUT_RESEARCH_NAME_YOU_WANT_TO_ADD);
    String researchName = inputString();
    boolean isAddSuccess = user.addResearch(researchName);
    System.out.println(isAddSuccess ? ADD_SUCCESS : ADD_FAILURE);
  }

  private static void showAdminInterface() {
    System.out.println(PLEASE_INPUT_USERNAME);
    String username = inputString();
    System.out.println(PLEASE_INPUT_PASSWORD);
    String password = inputString();
    Administrator admin = new Administrator();
    if (admin.isAdminAccountCorrect(username, password)) {
      adminInterface(admin);
    } else {
      System.out.println(YOUR_ADMIN_ACCOUNT_IS_INCORRECT);
      show();
    }
  }

  private static void adminInterface(Administrator admin) {
    System.out.println(String.format(ADMIN_INTERFACE, admin.getUsername()));
    int index = inputInt();
    adminOption(index, admin);
  }

  private static void adminOption(int index, Administrator admin) {
    switch (index) {
      case 1:
        admin.viewResearchRankingList();
        adminInterface(admin);
        break;
      case 2:
        addResearch(admin);
        adminInterface(admin);
        break;
      case 3:
        System.out.println(PLEASE_INPUT_SUPER_RESEARCH_NAME_YOU_WANT_TO_ADD);
        String researchName = inputString();
        admin.addSuperResearch(researchName);
        System.out.println(ADD_SUCCESS);
        adminInterface(admin);
        break;
      case 4:
        show();
        break;
      default:
        System.out.println(THE_INPUT_INDEX_IS_INCORRECT);
        adminInterface(admin);
    }
  }
}