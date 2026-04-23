# Blackjack

콘솔 기반 블랙잭 게임 구현 프로젝트입니다.

## 실행 방법

```bash
./gradlew run
```

> Java 21 이상 필요

## 게임 규칙

- 플레이어는 게임 시작 전 베팅 금액을 설정합니다.
- 딜러와 플레이어 모두 처음에 카드 2장을 받습니다. 딜러의 카드 1장은 공개되지 않습니다.
- 플레이어는 카드 합이 21을 넘지 않는 범위에서 추가 카드를 요청할 수 있습니다.
- 딜러는 카드 합이 16 이하이면 반드시 카드를 1장 더 받습니다.
- 카드 합이 21을 초과하면 Bust로 패배합니다.

### 수익 계산

| 결과 | 수익 |
|------|------|
| 블랙잭 (첫 두 장 합산 21) | 베팅 금액 × 1.5 |
| 블랙잭 무승부 (딜러도 블랙잭) | 베팅 금액 반환 |
| 일반 승리 | 베팅 금액 × 1 |
| 무승부 | 베팅 금액 반환 |
| 패배 또는 Bust | 베팅 금액 몰수 |

## 패키지 구조

```
src/main/java/
├── controller/
│   ├── BlackJackController.java   # 게임 흐름 전체 조율
│   ├── BlackJackGame.java         # 베팅 수집 · 카드 진행 · 수익 정산
│   └── Continuation.java         # 히트/스탠드 입력 enum
├── model/
│   ├── card/                      # Card, CardShape, CardValue, Cards
│   ├── participant/               # Dealer, Player, Players, Participants
│   ├── state/                     # 상태 패턴 (Hit, Stay, Bust, BlackJack …)
│   ├── betting/                   # BettingMoney, BettingCalculator
│   └── CardDispenser.java
└── view/
    ├── InputView.java
    └── OutputView.java
```

## 설계 특징

- **상태 패턴**: 참가자의 게임 상태(Hit / Stay / Bust / BlackJack)를 별도 객체로 분리
- **MVC 레이어 분리**: Controller가 View 호출을 전담하고 Model은 순수 도메인 로직에 집중
- **Ace 유연 처리**: Ace는 1 또는 11로 자동 계산하여 합이 21을 초과하지 않도록 조정

## 테스트 실행

```bash
./gradlew test
```