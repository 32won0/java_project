package TextRpg;

import java.util.Scanner;

class Raid {
    private Scanner scanner; // 사용자 입력을 위한 스캐너 인스턴스

    // 생성자
    public Raid() {
        this.scanner = new Scanner(System.in); // 스캐너 초기화
    }

    // 레이드 시작 메서드
    public void start() {
        System.out.println("레이드에 입장했습니다! 패턴을 피해 공격하세요!");

        // 첫 번째 패턴 수행
        if (performFirstPattern()) {
            // 두 번째 패턴 수행
            if (performSecondPattern()) {
                // 마지막 패턴 공격 수행
                if (performFinalPatternAttack(
                        "드래곤이 숨을 고르고 마지막 힘을 쏟아 불을 쏩니다! 피하세요!\n 1)왼쪽\n 2)오른쪽\n 3)공격\n 4)방어를 선택하세요.")) {
                    gameEnd(); // 레이드 성공
                }
            }
        }
    }

    // 첫 번째 패턴 수행 메서드
    private boolean performFirstPattern() {
        System.out.println("드래곤이 꼬리를 휘두릅니다.\n 1)왼쪽 혹은 2)오른쪽을 선택하세요.");
        int choice = scanner.nextInt(); // 사용자 입력 받기

        // 첫 번째 패턴에서 2번(오른쪽)이 올바른 선택
        if (choice != 2) {
            gameOver(); // 실패 시 게임 오버 처리
            return false; // 패턴 실패
        }

        // 패턴 회피 성공
        System.out.println("패턴을 회피했습니다! 공격 혹은 방어를 선택하세요.");
        System.out.println("1) 공격 2) 방어");
        choice = scanner.nextInt(); // 사용자 입력 받기

        if (choice == 1) { // 공격 선택
            System.out.println("드래곤 공격에 성공했습니다!");
            return true; // 패턴 성공
        } else {
            System.out.println("방어 성공! 하지만 드래곤이 기회를 놓치지 않았습니다.");
        }

        return false; // 패턴 실패
    }

    // 두 번째 패턴 수행 메서드
    private boolean performSecondPattern() {
        System.out.println("드래곤이 꼬리를 다시 휘두릅니다.\n 1)왼쪽 혹은 2)오른쪽을 선택하세요.");
        int choice = scanner.nextInt(); // 사용자 입력 받기

        // 두 번째 패턴에서 1번(왼쪽)이 올바른 선택
        if (choice != 1) {
            gameOver(); // 실패 시 게임 오버 처리
            return false; // 패턴 실패
        }

        // 패턴 회피 성공
        System.out.println("패턴을 회피했습니다! 공격 혹은 방어를 선택하세요.");
        System.out.println("1) 공격 2) 방어");
        choice = scanner.nextInt(); // 사용자 입력 받기

        if (choice == 1) { // 공격 선택
            System.out.println("드래곤 공격에 성공했습니다!");
            return true; // 패턴 성공
        } else {
            System.out.println("방어 성공! 하지만 드래곤이 기회를 놓치지 않았습니다.");
        }

        return false; // 패턴 실패
    }

    // 마지막 패턴 공격 수행 메서드
    private boolean performFinalPatternAttack(String message) {
        System.out.println(message);
        int choice = scanner.nextInt(); // 사용자 입력 받기

        // 마지막 패턴에서 3번(공격)이 올바른 선택
        if (choice != 3) {
            gameOver(); // 실패 시 게임 오버 처리
            return false; // 최종 공격 실패
        }

        return true; // 최종 공격 성공
    }

    // 게임 오버 처리 메서드
    private void gameOver() {
        System.out.println("회피 실패! 게임 오버! 당신은 레이드에서 사망했습니다.");
        System.out.println("게임이 종료되었습니다.");
        System.exit(0); // 게임 종료
    }

    // 레이드 성공 처리 메서드
    private void gameEnd() {
        System.out.println("드래곤이 패배했습니다! 당신은 영웅입니다!");
        System.out.println("드래곤을 물리친 당신의 용기와 결단에 찬사를 보냅니다.");
        System.out.println("당신의 여정은 여기서 끝나지만, 전설은 계속됩니다.");
        System.out.println("게임이 끝났습니다. 축하합니다!");
        System.exit(0); // 게임 종료
    }
}
