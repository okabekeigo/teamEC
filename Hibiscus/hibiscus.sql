set names utf8;
set foreign_key_checks=0;

drop database if exists hibiscus;
create database if not exists hibiscus;
  use hibiscus;

create table user_info(
  	id int not null primary key auto_increment comment"ID",
  	user_id varchar(16)unique not null comment"ユーザーID",
  	password varchar(16)not null comment"パスワード",
  	family_name varchar(32) not null comment "姓",
	first_name varchar(32) not null comment "名",
	family_name_kana varchar(32) not null comment "姓かな",
	first_name_kana varchar(32) not null comment "名かな",
	sex tinyint default '0' not null comment "性別",
	email varchar(32) not null comment "メールアドレス",
	status tinyint default '0' not null comment "ステータス",
	logined tinyint default '0' not null comment "ログインフラグ",
	regist_date datetime not null comment "登録日",
	update_date datetime comment "更新日"
)
default charset=utf8 comment"会員情報テーブル";

insert into user_info values
(1,"guest","guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー",0,"guest@gmail.com",0,0,now(),now()),
(2,"guest2","guest2","インターノウス","ゲストユーザー2","いんたーのうす","げすとゆーざー2",0,"guest2@gmail.com",0,0,now(),now()),
(3,"guest3","guest3","インターノウス","ゲストユーザー3","いんたーのうす","げすとゆーざー3",0,"guest3@gmail.com",0,0,now(),now()),
(4,"guest4","guest4","インターノウス","ゲストユーザー4","いんたーのうす","げすとゆーざー4",0,"guest4@gmail.com",0,0,now(),now()),
(5,"guest5","guest5","インターノウス","ゲストユーザー5","いんたーのうす","げすとゆーざー5",0,"guest5@gmail.com",0,0,now(),now()),
(6,"guest6","guest6","インターノウス","ゲストユーザー6","いんたーのうす","げすとゆーざー6",0,"guest6@gmail.com",0,0,now(),now()),
(7,"guest7","guest7","インターノウス","ゲストユーザー7","いんたーのうす","げすとゆーざー7",0,"guest7@gmail.com",0,0,now(),now()),
(8,"guest8","guest8","インターノウス","ゲストユーザー8","いんたーのうす","げすとゆーざー8",0,"guest8@gmail.com",0,0,now(),now()),
(9,"guest9","guest9","インターノウス","ゲストユーザー9","いんたーのうす","げすとゆーざー9",0,"guest9@gmail.com",0,0,now(),now()),
(10,"guest10","guest10","インターノウス","ゲストユーザー10","いんたーのうす","げすとゆーざー10",0,"guest10@gmail.com",0,0,now(),now()),
(11,"guest11","guest11","インターノウス","ゲストユーザー11","いんたーのうす","げすとゆーざー11",0,"guest11@gmail.com",0,0,now(),now()),
(12,"guest12","guest12","インターノウス","ゲストユーザー12","いんたーのうす","げすとゆーざー12",0,"guest12@gmail.com",0,0,now(),now())
;

create table product_info(
  	id int not null primary key auto_increment comment"ID",
  	product_id int unique not null comment"商品ID",
  	product_name varchar(100) unique not null comment "商品名",
	product_name_kana varchar(100) unique not null comment "商品名かな",
	product_description varchar(255) not null comment "商品詳細",
	category_id int not null comment "カテゴリID",
	price int comment "価格",
	image_file_path varchar(100) comment "画像ファイルパス",
	image_file_name varchar(50) comment "画像ファイル名",
	release_date datetime not null comment "発売年月",
	release_company varchar(50) comment "発売会社",
	status tinyint default '0' not null comment "ステータス",
	regist_date datetime not null comment "登録日",
	update_date datetime comment "更新日",
	foreign key(category_id) references m_category(category_id)
)
default charset=utf8 comment"商品情報テーブル";

insert into product_info values
  ( 1, 1,"八海山","はっかいさん","特別本醸造酒",2,200,"./images","nihonsyu_1.jpg",now(),"発売会社",0,now(),now()),
  ( 2, 2,"琥泉","こせん","純米吟醸",2,300,"./images","nihonsyu_2.jpg",now(),"発売会社",0,now(),now()),
  ( 3, 3,"獺祭","だっさい","純米大吟醸",2,400,"./images","nihonsyu_3.jpg",now(),"発売会社",0,now(),now()),
  ( 4, 4,"十四代","じゅうよんだい","純米吟醸",2,300,"./images","nihonsyu_4.jpg",now(),"発売会社",0,now(),now()),
  ( 5, 5,"鳳凰美田","ほうおうびでん","純米吟醸",2,350,"./images","nihonsyu_5.jpg",now(),"発売会社",0,now(),now()),
  ( 6, 6,"ランブルスコ","らんぶるすこ","赤ワイン",3,250,"./images","wine_1.jpg",now(),"発売会社",0,now(),now()),
  ( 7, 7,"カザマッタ","かざまった","赤ワイン",3,150,"./images","wine_2.jpg",now(),"発売会社",0,now(),now()),
  ( 8, 8,"ボジョレーヌーボー","ぼじょれーぬーぼー","赤ワイン",3,300,"./images","wine_3.jpg",now(),"発売会社",0,now(),now()),
  ( 9, 9,"シャトーメルシャン","しゃとーめるしゃん","白ワイン",3,500,"./images","wine_4.jpg",now(),"発売会社",0,now(),now()),
  ( 10, 10,"ソーヴィニヨン","そーゔぃによん","白ワイン",3,250,"./images","wine_5.jpg",now(),"発売会社",0,now(),now()),
  ( 11, 11,"角瓶","かくびん","ジャパニーズウィスキー",4,120,"./images","whiskey_1.jpg",now(),"発売会社",0,now(),now()),
  ( 12, 12,"知多","ちた","ジャパニーズウィスキー",4,200,"./images","whiskey_2.jpg",now(),"発売会社",0,now(),now()),
  ( 13, 13,"ジョニーウォーカーブラックラベル","じょにーうぉーかーぶらっくらべる","スコッチ",4,250,"./images","whiskey_3.jpg",now(),"発売会社",0,now(),now()),
  ( 14, 14,"ニッカ","にっか","ジャパニーズウィスキー",4,300,"./images","whiskey_4.jpg",now(),"発売会社",0,now(),now()),
  ( 15, 15,"メーカーズマーク","めーかーずまーく","バーボン",4,100,"images","whiskey_5.jpg",now(),"発売会社",0,now(),now())
  ;

  create table cart_info(
  id int not null primary key auto_increment comment "ID",
	user_id varchar(16) not null comment "ユーザーID",
	temp_user_id varchar(16) comment "仮ユーザーID",
	product_id int not null comment "商品ID",
	product_count int not null comment "個数",
	price int not null comment "金額",
	regist_date datetime not null comment "登録日",
	update_date datetime comment "更新日"
  )
  default charset=utf8 comment"カート情報テーブル";

  create table purchase_history_info(
  	id int not null primary key auto_increment comment "ID",
	user_id varchar(16) not null comment "ユーザーID",
	product_id int not null comment "商品ID",
	product_count int not null comment "個数",
	price int not null comment "金額",
	destination_id int not null comment "宛先情報ID",
	regist_date datetime not null comment "登録日",
	update_date datetime not null comment "更新日",
	foreign key(user_id) references user_info(user_id),
	foreign key(product_id) references product_info(product_id)
  )
  default charset=utf8 comment"購入履歴情報テーブル";

  create table destination_info(
	id int primary key not null auto_increment comment "ID",
	user_id varchar(16) not null comment "ユーザーID",
	family_name varchar(32) not null comment "姓",
	first_name varchar(32) not null comment "名",
	family_name_kana varchar(32) not null comment "姓かな",
	first_name_kana varchar(32) not null comment "名かな",
	email varchar(32) not null comment "メールアドレス",
	tel_number varchar(13) not null comment "電話番号",
	user_address varchar(50) not null comment "住所",
	regist_date datetime not null comment "登録日",
	update_date datetime not null comment "更新日"
)
default charset=utf8 comment="宛先情報テーブル";

insert into destination_info values
 (1,"guest","インターノウス","テストユーザー","いんたーのうす","てすとゆーざー","guest@internous.co.jp","080-1234-5678","東京都千代田区三番町１ー１　ＫＹ三番町ビル１Ｆ",now(),now())
 ;

 create table m_category(
   id int not null primary key comment"ID",
   category_id int unique not null comment"カテゴリID",
   category_name varchar(20) unique not null comment"カテゴリ名",
   category_description varchar(100) comment"カテゴリ詳細",
   insert_date datetime not null comment"登録日",
   update_date datetime comment"更新日"
 )
 default charset=utf8 comment"カテゴリマスタテーブル";

 insert into m_category values
   (1,1,"全てのカテゴリー","全商品が対象",now(),now()),
   (2,2,"日本酒","日本酒が対象",now(),now()),
   (3,3,"ワイン","ワインが対象",now(),now()),
   (4,4,"ウィスキー","ウィスキーが対象",now(),now())
   ;
