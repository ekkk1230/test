# 골목지도 · 새 Codex 채팅 이어가기

이 파일을 집에서 새 Codex 채팅에 첨부하고 아래 문장을 보내면 된다.

> 이 파일은 이전 채팅의 학습 인수인계야. 프로젝트 코드와 학습 기록을 확인하고, **후기 폼의 오류 후 재제출 확인부터** 이어가 줘. 그다음 메모리에 후기 저장하는 작은 예제를 설명해 줘. 프로젝트 코드는 내가 직접 작성할게.

## 내가 원하는 학습 방식

- 코드를 보면 대략 읽을 수 있고, 빈 파일에서 혼자 구현할 때는 단계별 연습이 필요하다. Java 21 기본부터 심화까지 교재처럼 배우면서 Spring MVC, Thymeleaf, MySQL로 맛집 지도에 적용하고 싶다.
- **개념 설명 → 작은 예제 → 내가 푸는 연습 문제 → 코드와 설명 리뷰 → 이해가 확인되면 프로젝트 적용 과제** 순서로 진행한다. 한 번에 현재 과제에 필요한 개념을 다룬다.
- 새 개념을 설명하기 전에 `LEARNING_LOG.md`를 참고한다. 예제에서 새 자바 문법이나 타입이 나오면 설명과 짧은 예제, 이해도 확인 질문을 붙인다.
- 구현은 내가 한다. 막혀서 힌트를 요청하면 막힌 부분을 좁힌 작은 힌트부터 준다. 내가 코드 확인을 요청할 때는 평소에 실행을 마친 상태이므로 소스 중심으로 리뷰한다. 오류가 있다고 하면 해당 흐름부터 확인한다.
- `LEARNING_LOG.md`에 진행, 이해도, 잘한 점, 보완점, 다음 실습을 함께 적는다. `261119.md`와 `261120.md`는 복습 요약이고 `PROJECT_PLAN.md`는 화면과 구현 순서다.

## 프로젝트와 집에서 열기

가칭 **골목지도**는 내가 등록한 맛집의 공개 지도다. 탐색, 맛집 상세, 후기, 댓글, 후기 좋아요, 주간 조회수 상위 최대 5곳을 목표로 한다. 순위 집계 기간은 한국 시간 월요일 00:00부터 다음 월요일 00:00 직전까지로 정했다.

1. 집 PC에 같은 프로젝트 소스와 이 파일을 가져온다. Git으로 옮긴다면 현재 코드와 문서가 들어 있는 커밋을 사용한다. 이 파일에는 소스 자체가 포함되어 있지 않다.
2. IntelliJ에서 `build.gradle`이 있는 폴더를 열고 JDK 21을 지정한다. 앱은 Spring Boot 4.1.2, Gradle Groovy, Spring MVC, Thymeleaf, YAML 구성이다. 현재 데이터는 메모리에 있고 MySQL이나 지도 API 설정은 뒤 단계다.
3. `TestApplication.main()`을 실행한다. PowerShell이 JDK를 잡고 있다면 프로젝트 루트에서 `./gradlew.bat bootRun`도 가능하다. `Started TestApplication`을 확인하고 `http://localhost:8080/restaurants`에 접속한다.
4. 콘솔 한글이 깨지면 IntelliJ의 콘솔 기본 인코딩이 UTF-8인지 확인한다. 이전 PC에서는 Gradle 설정의 빌드 및 실행을 IntelliJ IDEA로 바꾼 뒤 콘솔 한글을 정상 출력했다. 테스트 실행 설정은 일반 `main()` 실행과 별개다.

## 지금까지 배운 내용

- 자바 기초에서는 자료형, 형 변환과 정수 나눗셈, 비교와 논리 연산, `if`와 반복문, 배열, 매개변수와 반환값을 배웠다. `99`와 `100`처럼 경계값 앞뒤를 실행하는 습관을 연습했다.
- 객체지향에서는 생성자, `this`, 참조 복사, `private`, getter, 인스턴스 메서드, 객체 간 참조, `extends`, `implements`, `super`, 오버라이딩, 다형성, `null`, `throw`와 `try`/`catch`를 다뤘다. 부모 타입으로 호출할 수 있는 메서드를 판단하고, 실행되는 오버라이딩 구현은 실제 객체가 정한다.
- 컬렉션에서는 `List`/`ArrayList`, `Map`/`HashMap`, `Set`/`HashSet`, 제네릭, `Integer`와 오토박싱, `equals()`와 `hashCode()`를 연습했다. `getOrDefault()`는 기본값으로 조회하며 `put()`이 맵을 변경한다.
- 람다와 스트림에서는 `filter`, `map`, `sorted`, `limit`, `toList`와 `Optional`의 `findFirst` 결과, `map`, `orElse`를 다뤘다. `List.sort()`는 원본 순서를 바꾸고 `stream().sorted()`는 정렬된 순서로 요소를 전달한다. `LocalDate`로 주간 범위의 두 경계를 확인했다.
- 웹에서는 `@Controller`와 `@RestController`, 요청 경로와 뷰 이름, `Model`, `th:each`, `th:text`, `th:href`, `@PathVariable`, 안내 화면과 HTTP 404, 폼의 GET/POST, `th:action`, 입력 `name`과 `@RequestParam`의 연결을 실습했다.

## 코드에서 어디부터 볼지

| 파일 또는 화면 | 현재 역할 |
| --- | --- |
| `src/main/java/com/example/test/VariablePractice.java` | 자바 콘솔 실습을 모아 둔 곳 |
| `src/main/java/com/example/test/WelcomeController.java` | 메뉴·책 상세, 책 감상 폼 등 독립 연습 |
| `src/main/java/com/example/test/controller/RestaurantController.java` | 맛집 목록과 상세 조회, 후기 폼 GET/POST |
| `src/main/java/com/example/test/domain/Restaurant.java` | 맛집 객체, 이름·평점 등 필드와 동작 |
| `src/main/resources/templates/` | 목록, 상세, 404 안내, 후기 폼 HTML |

- `/restaurants`에는 예시 맛집 세 곳이 있다. `entrySet()`에서 각 행의 `item.key`로 ID와 링크를 만들고 `item.value`에서 `Restaurant`의 이름과 평점을 읽는다. 링크를 누르면 새 GET 요청이 가고 상세 메서드가 `@PathVariable`로 ID를 받는다.
- `/restaurants/2`는 위자네 상세, `/restaurants/99`는 안내 화면과 404를 반환하는 흐름까지 구현했다. `/restaurants/2/reviews/new`는 후기 입력 화면이다. 정상 폼 제출 후 입력값을 화면에 표시하는 동작은 사용자가 확인했다. 후기 저장은 아직 없다.

## 다음 채팅에서 시작할 지점

현재는 `Map.of()`로 예시 맛집을 초기화하고 후기 GET과 POST 양쪽에서 ID를 조회해 없는 맛집을 404로 처리한다. 이름은 서버에서 조회한다. POST는 평점과 본문을 문자열로 받아, 빈 입력과 숫자 변환 실패 및 유한한 `0.0`~`5.0` 범위를 검사한다. 실패하면 `draftRating`과 `draftComment`로 기존 입력을 복원하고 성공하면 입력칸을 비운 뒤 결과를 표시한다.

1. **오류 후 재제출 확인** · `/restaurants/2/reviews/new`에서 `abc`와 정상 본문을 제출해 두 값이 남는지 확인한다. 평점만 `4.5`로 고쳐 다시 제출하면 입력칸이 비고 결과가 표시돼야 한다. 소스는 검토했으며 이 브라우저 실행 결과는 아직 공유되지 않았다.
2. **메모리 저장 학습** · 검증된 입력으로 후기 객체를 만들고 목록에 담는 작은 예제를 배운 뒤, 이해를 확인하고 프로젝트 과제로 맛집 상세에서 후기를 읽어 표시한다.
3. **이후 계획** · MySQL에 후기 저장 → 로그인·댓글·좋아요 → 지도 API 연결 → 날짜별 조회 기록과 주간 순위 순으로 진행한다.
