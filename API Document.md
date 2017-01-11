#BigHead-Server
# 코드 등록
 * 요청
  * POST
  * `/API/Code/create`
 * 인자 
  * code : String
 * 동작
  * 코드를 등록한다.
 * 반환 값
  * 실행 성공 `{"success":true}`
  * 실행 실패 `{"success":false}`
  
  
# 코드 확인
 * 요청
  * POST
  * `/API/Code/check`
 * 인자 
  * code : String
  * imei : String
 * 동작
  * 데이터베이스에 등록된 코드를 확인한다.
 * 반환 값
  * 실행 성공 `{"success":true}`
  * 실행 실패 `{"success":false}`
  

# 코드 사용 요청
 * 요청
  * POST
  * `/API/Code/register`
 * 인자 
  * code : String
  * imei : String
 * 동작
  * 데이터베이스의 imei값이 null인지 확인하고, 아니면 사용자 디바이스의 imei값을 DB에 등록한다.
 * 반환 값
  * 실행 성공 `{"success":true}`
  * 실행 실패 `{"success":false}`
  
# 코드 리스트
 * 요청
  * GET
  * `/API/Code/list`
 * 인자 
  * (없음)
 * 동작
  * 데이터베이스에 등록된 코드와 imei값을 json 형태로 반환한다.
 * 반환 값
  * 실행 성공 `{"data":"[{code=?, imei=?}, {...}, {...}, ...]"}"`