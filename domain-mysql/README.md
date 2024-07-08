# domain-mysql
## 개요
Blocking 방식의 Driver 를 사용한 mysql 용 domain module.
## 구성
### domain
Jpa Entity class (영속성 객체)
### dto
- param : 외부 module 에서 요청을 받을 때 사용하는 dto object.
- data : 외부 module 에 data 를 넘겨줄 때 사용하는 dto object.
### repository
Master DB 에서 query 를 수행하는 repository
### read.repository
Read DB 에서 query 를 수행하는 repository