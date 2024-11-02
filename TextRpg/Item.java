package TextRpg;

// 아이템 추상 클래스
abstract class Item {
    // 아이템 사용 메서드 (구현은 하위 클래스에서)
    public abstract void use(Player player);
}

// 치유 아이템 클래스
class HealingItem extends Item {
    // 플레이어에게 치유 효과 적용
    public void use(Player player) {
        player.heal(50); // 체력 50 회복
        System.out.println("체력이 50 회복되었습니다."); // 회복 메시지 출력
    }
}

// 무기 클래스
class Weapon extends Item {
    // 플레이어의 공격력 증가
    public void use(Player player) {
        player.increaseAttackPower(10); // 공격력 10 증가
        System.out.println("공격력이 10 증가했습니다."); // 증가 메시지 출력
    }
}

// 방어 아이템 클래스
class ShieldItem extends Item {
    // 플레이어의 방어 상태 활성화
    public void use(Player player) {
        player.activateShield(); // 방어 활성화
        System.out.println("방어 아이템을 사용했습니다. 다음 공격 실패 시 피해를 막아줍니다."); // 방어 아이템 사용 메시지 출력
    }
}

// 악세서리 클래스
class Accessory extends Item {
    // 플레이어의 공격 확률 증가
    public void use(Player player) {
        player.increaseAttackProbability(0.01); // 공격 확률 1% 증가
        System.out.println("공격 확률이 1% 증가했습니다."); // 증가 메시지 출력
    }
}
