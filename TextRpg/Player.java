package TextRpg;

// 플레이어 클래스
class Player {
    private int health;              // 플레이어의 체력
    private int attackPower;         // 플레이어의 공격력
    private int level;               // 플레이어의 레벨
    private int experience;          // 플레이어의 경험치
    private double attackProbability; // 공격 성공 확률
    private boolean shieldProtection; // 방어 아이템 효과 여부

    // 생성자: 플레이어의 초기 상태 설정
    public Player() {
        this.health = 300;                   // 초기 체력 300
        this.attackPower = 50;               // 초기 공격력 50
        this.level = 1;                       // 초기 레벨 1
        this.experience = 0;                  // 초기 경험치 0
        this.attackProbability = 0.7;         // 기본 공격 확률 70%
        this.shieldProtection = false;        // 방어 아이템 효과 비활성화
    }

    // 공격 시도 메서드: 공격 성공 여부를 반환
    public boolean attack() {
        return Math.random() < attackProbability; // 공격 성공 확률에 따라 true 또는 false 반환
    }

    // 방어 메서드: 방어 성공 메시지 출력
    public void defend() {
        System.out.println("방어 성공! 데미지를 막았습니다.");
    }

    // 경험치 요구량 계산 메서드: 현재 레벨에 따라 필요한 경험치를 반환
    private int getExperienceNeeded() {
        return level * 10; // 레벨에 따라 경험치 요구량 계산
    }

    // 경험치 획득 및 레벨업 체크 메서드
    public void gainExperience(int xp) {
        experience += xp; // 경험치 추가
        System.out.println("경험치 획득! 현재 경험치: " + experience);
        // 레벨업 조건 충족 시 레벨업 수행
        while (experience >= getExperienceNeeded()) {
            experience -= getExperienceNeeded(); // 필요한 경험치만큼 감소
            levelUp(); // 레벨업 메서드 호출
        }
    }

    // 레벨업 메서드: 레벨 증가 및 능력치 증가
    public void levelUp() {
        level++; // 레벨 증가
        health += 50; // 체력 증가
        attackPower += 10; // 공격력 증가
        System.out.println("레벨업! 현재 레벨: " + level + ", 체력: " + health + ", 공격력: " + attackPower);
        if (level >= 30) { // 레벨이 30이 될 때 레이드 시작
            startRaid(); // 레이드 시작 메서드 호출
        }
    }

    // 체력 회복 메서드
    public void heal(int amount) {
        health += amount; // 체력 증가
        System.out.println("체력 회복! 현재 체력: " + health);
    }

    // 공격력 증가 메서드
    public void increaseAttackPower(int amount) {
        attackPower += amount; // 공격력 증가
        System.out.println("공격력이 증가했습니다! 현재 공격력: " + attackPower);
    }

    // 공격 확률 증가 메서드
    public void increaseAttackProbability(double amount) {
        attackProbability += amount; // 공격 확률 증가
        System.out.println("공격 확률이 증가했습니다! 현재 확률: " + attackProbability);
    }

    // 방어 아이템 활성화 메서드
    public void activateShield() {
        shieldProtection = true; // 방어 아이템 활성화
    }

    // 방어 아이템 효과 여부 반환 메서드
    public boolean hasShieldProtection() {
        return shieldProtection; // 방어 아이템 효과 여부 반환
    }

    // 방어 아이템 비활성화 메서드
    public void deactivateShield() {
        shieldProtection = false; // 방어 아이템 비활성화
    }

    // 현재 공격 확률 반환 메서드
    public double getAttackProbability() {
        return attackProbability; // 현재 공격 확률 반환
    }

    // 레이드 시작 메서드
    public void startRaid() {
        Raid raid = new Raid(); // Raid 인스턴스 생성
        raid.start(); // 레이드 시작
    }

    // 현재 체력 반환 메서드
    public int getHealth() {
        return health; // 현재 체력 반환
    }

    // 체력 감소 메서드
    public void reduceHealth(int amount) {
        health = Math.max(0, health - amount); // 체력이 0 이하로 떨어지지 않도록 조정
    }

    // 현재 공격력 반환 메서드
    public int getAttackPower() {
        return attackPower; // 현재 공격력 반환
    }

    // 현재 레벨 반환 메서드
    public int getLevel() {
        return level; // 현재 레벨 반환
    }
}
