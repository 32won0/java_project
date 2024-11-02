package TextRpg;

import java.util.Random;
import java.util.Scanner;

// 게임 클래스
class Game {
    private Player player; // 플레이어 인스턴스
    private Scanner scanner; // 사용자 입력을 위한 스캐너
    private Forest forest; // 숲 인스턴스
    private Sea sea; // 바다 인스턴스
    private Random random; // 랜덤 숫자를 생성하기 위한 인스턴스

    // 생성자
    public Game() {
        player = new Player(); // 플레이어 초기화
        scanner = new Scanner(System.in); // 스캐너 초기화
        forest = new Forest(player); // 숲 인스턴스 생성
        sea = new Sea(player); // 바다 인스턴스 생성
        random = new Random(); // 랜덤 인스턴스 생성
    }

    // 게임 시작 메서드
    public void start() {
        System.out.println(
                "깊고 어두운 숲, 전설 속 영웅의 길이 당신 앞에 펼쳐집니다.\n잃어버린 왕국의 땅을 지키기 위해 몬스터와 맞서야 할 시간입니다.\n저 멀리 괴물의 울음소리가 들리고, 당신의 모험이 이제 막 시작되었습니다.");
        System.out.println("");

        while (true) {
            showMainMenu(); // 메인 메뉴 표시
        }
    }

    // 메인 메뉴 표시 메서드
    private void showMainMenu() {
        System.out.println("어디로 가시겠습니까?");
        System.out.println("1) 숲으로 간다");
        System.out.println("2) 바다로 간다");
        System.out.println("3) 마을로 돌아간다.");

        int choice = getUserInput(); // 사용자 입력 받기

        switch (choice) {
            case 1:
                forest.enter(); // 숲으로 이동
                break;
            case 2:
                sea.enter(); // 바다로 이동 
                break;
            case 3:
                handleReturnToVillage(); // 마을로 돌아가기 처리
                break;
            default:
                System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
        }
    }

    // 사용자 입력을 처리하는 메서드
    private int getUserInput() {
        while (true) {
            try {
                return scanner.nextInt(); // 사용자로부터 숫자 입력 받기
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
                scanner.next(); // 잘못된 입력 처리
            }
        }
    }

    // 마을로 돌아가기 처리 메서드
    private void handleReturnToVillage() {
        System.out.println(
                "마을로 돌아갑니다...\n 어두운 숲 속에서 느껴지는 긴장감과 불안감이 당신을 감싸고, 바람의 속삭임과 나뭇잎의 흔들림은 두려움을 자아냅니다.\n 가슴이 두근거리며, 당신은 안전한 집으로 돌아가기로 결심합니다.");
        System.out.println(
                " 이 선택은 당신이 영웅이 되는 대신 평범한 농부로 살아가게 하였지만, 그 속에서 진정한 행복을 찾게 됩니다.\n 마을의 조용한 일상 속에서 작은 농장을 경영하며, 소중한 일상을 통해 더 큰 성공을 거두게 됩니다.");
        System.out.println("게임 오버\n당신의 모험은 여기서 끝이지만, 새로운 길이 열릴 수 있습니다. 다음 기회에 더 용감하게 다시 도전해 보세요!");
        System.exit(0); // 게임 종료
    }

    // 전투 시작 메서드
    public void startBattle(Monster monster) {
        System.out.println(monster.getName() + "와(과)의 전투가 시작되었습니다!");
        System.out.println(monster.getName() + " 체력: " + monster.getHealth());

        while (!monster.isDefeated() && player.getHealth() > 0) {
            System.out.println("1) 공격 2) 방어");
            int choice = getUserInput(); // 사용자 선택 받기

            if (choice == 1) { // 공격 선택
                double effectiveAttackProbability = player.getAttackProbability() - monster.getEvasionProbability();
                if (Math.random() < effectiveAttackProbability) {
                    monster.reduceHealth(player.getAttackPower()); // 몬스터 체력 감소
                    System.out.println("플레이어가 " + monster.getName() + "을(를) 공격했습니다! 남은 체력: " + monster.getHealth());
                } else {
                    System.out.println("공격이 실패했습니다!"); // 공격 실패 메시지
                    handleMonsterAttack(monster); // 몬스터 공격 처리
                }
            } else if (choice == 2) { // 방어 선택
                System.out.println("플레이어가 방어 자세를 취했습니다!");
            	player.defend(); // 방어 자세 취하기
            } else {
                System.out.println("잘못된 선택입니다. 다시 선택해 주세요.");
            }
        }

        // 전투 결과 처리
        if (player.getHealth() <= 0) {
            System.out.println("게임 오버\n당신의 모험은 여기서 끝이지만, 새로운 길이 열릴 수 있습니다. 다음 기회에 더 용감하게 다시 도전해 보세요!");
            System.exit(0);
        } else if (monster.isDefeated()) {
            System.out.println(monster.getName() + "을(를) 처치했습니다!");
            player.gainExperience(monster.getExperienceReward()); // 경험치 획득
            player.heal(10); // 체력 회복
            dropItem(); // 몬스터 처치 시 아이템 드롭 및 자동 사용
        }
    }

    // 몬스터 공격 처리 메서드
    private void handleMonsterAttack(Monster monster) {
        if (player.hasShieldProtection()) { // 방어 아이템이 있는지 확인
            player.deactivateShield(); // 방어 아이템 비활성화
            System.out.println("방어 아이템이 피해를 막아주었습니다!");
        } else {
            int damage = monster.attack(); // 몬스터 공격력 가져오기
            player.reduceHealth(damage); // 플레이어 체력 감소
            System.out.println(monster.getName() + "이(가) 플레이어를 공격했습니다! 플레이어 남은 체력: " + player.getHealth());
        }
    }

    // 아이템 드롭 메서드
    private void dropItem() {
        Item item;
        int itemType = random.nextInt(4); // 랜덤으로 아이템 타입 결정

        switch (itemType) {
            case 0 -> {
                item = new HealingItem();
                System.out.println("힐링 아이템을 획득했습니다."); // 아이템 획득 메시지
            }
            case 1 -> {
                item = new Weapon();
                System.out.println("무기를 획득했습니다."); // 아이템 획득 메시지
            }
            case 2 -> {
                item = new ShieldItem();
                System.out.println("방어 아이템을 획득했습니다."); // 아이템 획득 메시지
            }
            case 3 -> {
                item = new Accessory();
                System.out.println("액세서리를 획득했습니다."); // 아이템 획득 메시지
            }
            default -> throw new IllegalStateException("Unexpected value: " + itemType); // 예외 처리
        }

        // 아이템 자동 사용
        item.use(player);
    }
}
