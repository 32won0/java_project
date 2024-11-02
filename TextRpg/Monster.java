package TextRpg;

// 몬스터 클래스
class Monster {
    private String name;                // 몬스터의 이름
    private int health;                 // 몬스터의 체력
    private int attackPower;            // 몬스터의 공격력
    private int experienceReward;        // 몬스터를 처치했을 때 주는 경험치
    private double evasionProbability;   // 몬스터의 회피 확률

    // 생성자: 몬스터의 초기 속성 설정
    public Monster(String name, int health, int attackPower, int experienceReward, double evasionProbability) {
        this.name = name;                             // 몬스터 이름 초기화
        this.health = health;                         // 몬스터 체력 초기화
        this.attackPower = attackPower;               // 몬스터 공격력 초기화
        this.experienceReward = experienceReward;     // 경험치 보상 초기화
        this.evasionProbability = evasionProbability; // 회피 확률 초기화
    }

    // 몬스터 이름 반환 메서드
    public String getName() {
        return name; // 몬스터 이름 반환
    }

    // 몬스터 체력 반환 메서드
    public int getHealth() {
        return health; // 몬스터 체력 반환
    }

    // 몬스터 체력 감소 메서드
    public void reduceHealth(int amount) {
        health -= amount; // 지정된 양만큼 체력 감소
    }

    // 몬스터가 패배했는지 확인하는 메서드
    public boolean isDefeated() {
        return health <= 0; // 체력이 0 이하이면 패배로 간주
    }

    // 몬스터의 공격력 반환 메서드
    public int attack() {
        return attackPower; // 몬스터의 공격력 반환
    }

    // 경험치 보상 반환 메서드
    public int getExperienceReward() {
        return experienceReward; // 경험치 보상 반환
    }

    // 몬스터의 회피 확률 반환 메서드
    public double getEvasionProbability() {
        return evasionProbability; // 회피 확률 반환
    }

    // 몬스터 생성 메서드: 타입에 따라 몬스터를 생성
    public static Monster createMonster(String type) {
        switch (type.toLowerCase()) { // 입력된 타입을 소문자로 변환하여 비교
            case "deepseabeast":
                return new Monster("Deep Sea Beast", 500, 35, 80, 0.2); // 20% 회피 확률
            case "monster":
                return new Monster("Monster", 300, 20, 50, 0.15); // 15% 회피 확률
            case "mimic":
                return new Monster("Mimic", 180, 8, 15, 0.1); // 10% 회피 확률
            case "fairy":
                return new Monster("Fairy", 160, 15, 30, 0.25); // 25% 회피 확률
            case "mermaid":
                return new Monster("Mermaid", 120, 10, 20, 0.1); // 10% 회피 확률
            case "goblin":
                return new Monster("Goblin", 60, 5, 10, 0.05); // 5% 회피 확률
        }
        return null; // 지정된 타입이 없으면 null 반환
    }
}
