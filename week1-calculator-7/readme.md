# java-calculator-precourse
## 목표
- 테스트 커버리지 100% 이상
- 클린 코드 지향

## 구현할 기능 목록
1) 입출력 클래스 & 메소드 구현
    - 단위 테스트
2) 더하기 메소드 구현
    - 구분자를 통해 숫자를 구분하여 더하는 기능
    - 커스텀 구분자 를 포함하여 숫자를 구분하여 더하는 기능
    - 단위 테스트

## 예외 발생 상황
- 사용자가 잘못된 값을 입력하는 경우
    - IllegalArgumentException 발생시킨 후 애플리케이션은 종료

- 구분자를 통해 숫자를 구분하는 기능에서의 예외 처리
    - 1) 구분자가 생략된 문자열인 경우
    - 2) 문자열에 숫자가 없는 경우
    - 3) 잘못된 커스텀 지정자가 있는 경우
    - 4) 구분자로 분리하였을 때 Digit 이 음수가 포함된 경우