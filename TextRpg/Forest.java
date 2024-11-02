package TextRpg;

import java.util.Random;
import java.util.Scanner;

class Forest {
    private Player player; // 플레이어 객체
    private Random random; // 랜덤 객체
    private boolean[] eventTracker; // 이벤트 중복 방지용 배열
    private Scanner scanner; // 사용자 입력을 위한 스캐너

    public Forest(Player player) {
        this.player = player; // 생성자에서 플레이어 초기화
        this.random = new Random(); // 랜덤 객체 초기화
        this.eventTracker = new boolean[6]; // 총 6개의 이벤트를 위한 배열
        this.scanner = new Scanner(System.in); // 스캐너 초기화
    }

    public void enter() {
        System.out.println("숲에 들어갔습니다. 주변이 고요합니다..."); // 숲 진입 메시지
        triggerRandomEvents(); // 랜덤 이벤트 발생
    }

    private void triggerRandomEvents() {
        int eventsCount = 0; // 발생한 이벤트 수

        while (eventsCount < 6) { // 최대 6개의 랜덤 이벤트 발생
            int eventType = random.nextInt(6); // 랜덤 이벤트 타입 선택
            if (!eventTracker[eventType]) { // 중복되지 않은 이벤트만 발생
                eventTracker[eventType] = true; // 이벤트 발생 기록
                eventsCount++; // 발생한 이벤트 수 증가
                handleEvent(eventType); // 이벤트 처리
            }
        }
    }

    private void handleEvent(int eventType) { // 선택된 이벤트에 따라 처리
        switch (eventType) {
            case 0: // 페어리와의 조우
                System.out.println("페어리가 나타났습니다! 신비로운 분위기를 풍기며 다가옵니다.");
                encounterMonster("fairy"); // 몬스터 조우 처리
                break;
            case 1: // 보물 상자 발견
                System.out.println("나무 아래 숨겨진 보물 상자를 발견했습니다.");
                treasureChest(); // 보물 상자 처리
                break;
            case 2: // 적의 인기척
                System.out.println("길을 잃고 주변에서 적의 인기척을 느낍니다...");
                battleWithEnemies(); // 전투 처리
                break;
            case 3: // 신비로운 존재와의 조우
                System.out.println("신비로운 존재와 마주칩니다. 그들이 도움을 줄지 해가 될지 모릅니다.");
                meetMysticalBeing(); // 신비로운 존재 처리
                break;
            case 4: // 자연의 힘
                System.out.println("자연의 힘이 느껴지며 몸에 활기가 돕니다.");
                naturePower(); // 자연의 힘 처리
                break;
            case 5: // 주변 변화
                System.out.println("주변에서 나뭇가지가 흔들리고, 새로운 길이 열립니다.");
                changeInSurroundings(); // 주변 변화 처리
                break;
        }
    }

    private void encounterMonster(String monsterType) { // 몬스터와의 전투 처리
        Monster monster = Monster.createMonster(monsterType); // 몬스터 생성
        if (monster != null) {
            Game game = new Game(); 
            game.startBattle(monster); // 전투 시작
        }
    }

    private void treasureChest() { // 보물 상자 처리
        System.out.println("상자를 열겠습니까? 1) 열기, 2) 무시");
        int choice = scanner.nextInt(); // 사용자 선택 입력

        if (choice == 1) { // 상자를 열기로 선택
            if (random.nextBoolean()) { // 랜덤으로 보상 결정
                System.out.println("보물 상자에서 힘과 체력을 얻었습니다!");
                player.increaseAttackPower(30); // 공격력 증가
                player.heal(20); // 체력 회복
            } else {
                System.out.println("상자가 움직이며 미믹이 나타났습니다! 전투 시작.");
                encounterMonster("mimic"); // 미믹과 전투
            }
        } else {
            System.out.println("보물 상자를 무시하고 이동합니다."); // 상자를 무시
        }
    }

    private void battleWithEnemies() { // 적과 전투 처리
        System.out.println("주변의 작은 적들이 모여듭니다!");
        encounterMonster("goblin"); // 고블린과 전투
    }

    private void meetMysticalBeing() { // 신비로운 존재 처리
        System.out.println("도움을 요청하시겠습니까? 1) 요청, 2) 무시");
        int choice = scanner.nextInt(); // 사용자 선택 입력

        if (choice == 1) { // 도움 요청
            if (random.nextBoolean()) { // 랜덤으로 결과 결정
                System.out.println("신비로운 존재가 신비로운 버프를 주었습니다!");
                player.activateShield(); // 방패 활성화
                System.out.println("공격에 실패해도 1회 방어해 줍니다.");
            } else {
                System.out.println("신비로운 존재의 힘으로 체력이 절반 감소합니다.");
                player.reduceHealth(player.getHealth() / 2); // 체력 감소
            }
        } else {
            System.out.println("신비로운 존재을 무시하자, 전투가 시작됩니다.");
            encounterMonster("monster"); // 큰 몬스터와의 전투
        }
    }

    private void naturePower() { // 자연의 힘 처리
        System.out.println("자연의 힘을 받아들입니다. 1) 공격력 증가, 2) 체력 회복");
        int choice = scanner.nextInt(); // 사용자 선택 입력

        if (choice == 1) { // 공격력 증가 선택
            System.out.println("공격력이 증가했습니다.");
            player.increaseAttackPower(5); // 공격력 증가
        } else {
            System.out.println("체력이 회복되었습니다.");
            player.heal(20); // 체력 회복
        }
    }

    private void changeInSurroundings() { // 주변 변화 처리
        System.out.println("바다로 나가겠습니까? 1) 바다로 가기");
        int choice = scanner.nextInt(); // 사용자 선택 입력

        if (choice == 1) { // 바다로 가기로 선택
            System.out.println("바다로 향하며 새로운 모험이 시작됩니다.");
            Sea sea = new Sea(player); // Sea 인스턴스 생성
            sea.enter(); // 바다 이벤트 시작
        }
    }
}
