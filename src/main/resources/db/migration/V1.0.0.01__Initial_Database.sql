create table "user" (
    id bigserial primary key,
    username varchar,
    email varchar,
    "password" varchar
);

create table user_balance (
    id bigserial primary key,
    balance varchar,
    balance_achieve int4,
    "user_id" int8,
    CONSTRAINT fk_user
      FOREIGN KEY("user_id")
	  REFERENCES "user"(id)
);

create table user_balance_history(
    id bigserial primary key,
    user_balance_id int8,
    balance_before int8,
    balance_after int8,
    activity varchar,
    "type" varchar,
    ip varchar,
    "location" varchar,
    user_agent varchar,
    author varchar,
    CONSTRAINT fk_user_balance
      FOREIGN KEY("user_balance_id")
	  REFERENCES "user_balance"(id)
);

create table bank_balance(
    id bigserial primary key,
    balance int8,
    balance_achieve int8,
    code varchar,
    enable boolean
);

create table bank_balance_history(
    id bigserial primary key,
    bank_balance_id int8,
    balance_before int8,
    balance_after int8,
    activity varchar,
    "type" varchar,
    ip varchar,
    "location" varchar,
    user_agent varchar,
    author varchar,
    CONSTRAINT fk_bank_balance
      FOREIGN KEY("bank_balance_id")
	  REFERENCES "bank_balance"(id)
);