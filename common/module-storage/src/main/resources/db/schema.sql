CREATE TABLE IF NOT EXISTS `members` (
    `idx` serial PRIMARY KEY COMMENT '회원 Index',
    `name` varchar(20) NOT NULL COMMENT '회원 이름',
    `hits` int DEFAULT 0 COMMENT '회원 이름',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일자'
) COMMENT = '회원 정보';

CREATE TABLE IF NOT EXISTS `payments` (
    `idx` serial PRIMARY KEY COMMENT '결제 Index',
    `member_idx` bigint NOT NULL COMMENT '회원 Index',
    `order_id` varchar(50) NOT NULL COMMENT '주문번호',
    `status` varchar(10) NOT NULL COMMENT '주문상태',
    `amount` int DEFAULT 0 COMMENT '결제 금액',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일자'
) COMMENT = '결제 정보';

CREATE TABLE IF NOT EXISTS `points` (
    `idx` serial PRIMARY KEY COMMENT '포인트 Index',
    `member_idx` bigint NOT NULL COMMENT '회원 Index',
    `payment_idx` bigint NOT NULL COMMENT '결제 Index',
    `amount` int DEFAULT 0 COMMENT '결제 금액',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일자'
) COMMENT = '포인트 정보';