package TextRpg;

import java.util.Random;
import java.util.Scanner;

// Sea 클래스는 플레이어가 바다 지역에 진입했을 때 발생하는 이벤트를 관리합니다.
class Sea {
    private Player player; // 플레이어 객체
    private Random random; // 랜덤 이벤트 생성기
    private boolean[] eventTracker; // 이벤트 중복 방지용 배열
    private Scanner scanner; // 입력을 위한 스캐너 객체

    // 생성자: 플레이어 객체를 받아 초기화
    public Sea(Player player) {
        this.player = player;
        this.random = new Random(); // 랜덤 객체 초기화
        this.eventTracker = new boolean[5]; // 총 5개의 이벤트
        this.scanner = new Scanner(System.in); // 스캐너 초기화
    }

    // 바다에 진입했을 때 호출되는 메서드
    public void enter() {
        System.out.println("바다로 들어갔습니다. 주변은 물의 기운이 감돕니다...");
        triggerRandomEvents(); // 랜덤 이벤트 발생
    }

    // 랜덤 이벤트를 발생시키는 메서드
    private void triggerRandomEvents() {
        int eventsCount = 0; // 발생한 이벤트 수 카운트

        while (eventsCount < 5) { // 5개의 랜덤 이벤트 발생
            int eventType = random.nextInt(5); // 0~4 범위의 랜덤 숫자 생성
            if (!eventTracker[eventType]) { // 중복되지 않은 이벤트만 발생
                eventTracker[eventType] = true; // 이벤트 발생 기록
                eventsCount++; // 발생한 이벤트 수 증가
                handleEvent(eventType); // 이벤트 처리
            }
        }
    }

    // 생성된 이벤트 유형에 따라 이벤트를 처리하는 메서드
    private void handleEvent(int eventType) {
        switch (eventType) {
            case 0:
                System.out.println("수면 아래에서 반짝이는 보물 상자를 발견했습니다.");
                seaTreasureChest(); // 보물 상자 이벤트 처리
                break;
            case 1:
                System.out.println("깊은 바다에서 잠든 괴물을 발견했습니다.");
                sleepingMonster(); // 잠자는 괴물 이벤트 처리
                break;
            case 2:
                System.out.println("신비로운 바다 생물과 마주쳤습니다.");
                meetMysticalBeing(); // 신비로운 존재와의 만남 이벤트 처리
                break;
            case 3:
                System.out.println("파도가 갈라지며 새로운 길이 열립니다.");
                changeInSurroundings(); // 환경 변화 이벤트 처리
                break;
            case 4:
                System.out.println("바다의 힘이 느껴집니다.");
                seaPower(); // 바다의 힘 이벤트 처리
                break;
            default:
                System.out.println("이상한 일이 일어났지만 무사히 지나갔습니다."); // 기본 케이스
                break;
        }
    }

    // 몬스터와의 전투를 시작하는 메서드
    private void encounterMonster(String monsterType) {
        Monster monster = Monster.createMonster(monsterType); // 몬스터 생성
        if (monster != null) {
        	Game game = new Game(); 
        	game.startBattle(monster); // 전투 시작
        }
    }

    // 보물 상자를 열기 위한 메서드
    private void seaTreasureChest() {
        System.out.println("상자를 열겠습니까? 1) 열기, 2) 무시");
        int choice = scanner.nextInt(); // 사용자 선택 입력

        if (choice == 1) { // 상자를 열기로 선택한 경우
            if (random.nextBoolean()) { // 랜덤으로 아이템 획득 여부 결정
                System.out.println("바다 보물 상자에서 힘과 체력을 얻었습니다!");
                player.increaseAttackPower(30); // 공격력 증가
                player.heal(20); // 체력 회복
            } else {
                System.out.println("상자가 움직이며 바다 미믹이 나타났습니다! 전투 시작.");
                encounterMonster("mimic"); // 미믹 몬스터와의 전투
            }
        } else {
            System.out.println("바다 보물 상자를 무시하고 이동합니다."); // 상자를 무시한 경우
        }
    }

    // 잠자는 괴물과의 전투를 시작하는 메서드
    private void sleepingMonster() {
        System.out.println("1) 전투 시작");
        int choice = scanner.nextInt(); // 사용자 선택 입력

        if (choice == 1) { // 전투 시작을 선택한 경우
            encounterMonster("deepSeaBeast"); // 깊은 바다 괴물과의 전투
        } 
    }

    // 신비로운 존재와의 만남 처리 메서드
    private void meetMysticalBeing() {
        System.out.println("도움을 요청하시겠습니까? 1) 요청, 2) 무시");
        int choice = scanner.nextInt(); // 사용자 선택 입력

        if (choice == 1) { // 도움 요청을 선택한 경우
            System.out.println("신비로운 생물로부터 특별 버프를 받았습니다!");
            player.activateShield(); // 방어 아이템 활성화
            System.out.println("공격에 실패해도 1회 방어해 줍니다.");
        } else {
            System.out.println("신비로운 생물을 무시하자, 전투가 시작됩니다.");
            encounterMonster("seaSpirit"); // 바다 정령과의 전투
        }
    }

    // 환경 변화 이벤트 처리 메서드
    private void changeInSurroundings() {
        System.out.println("숲으로 가시겠습니까? 1) 숲으로 간다");
        int choice = scanner.nextInt(); // 사용자 선택 입력

        if (choice == 1) { // 숲으로 가기로 선택한 경우
            Forest forest = new Forest(player); // Forest 인스턴스 생성
            forest.enter(); // 숲 이벤트 시작
        }
    }

    // 바다의 힘을 받는 이벤트 처리 메서드
    private void seaPower() {
        System.out.println("1) 공격 버프를 얻는다, 2) 일회성 방어 버프를 얻는다");
        int choice = scanner.nextInt(); // 사용자 선택 입력

        if (choice == 1) { // 공격 아이템 획득 선택한 경우
            System.out.println("공격력이 증가했습니다.");
            player.increaseAttackPower(10); // 공격력 증가
        } else {
            System.out.println("방어 버프를 획득하였습니다.");
            player.activateShield(); // 방어 아이템 활성화
        }
    }
}
